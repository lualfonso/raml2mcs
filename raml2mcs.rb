require 'yaml'
require 'fileutils'
require 'erb'
require 'active_support/inflector'
require 'optparse'


class String
     def str_between marker1, marker2
       self[/#{Regexp.escape(marker1)}(.*?)#{Regexp.escape(marker2)}/m, 1]
     end
end

@root_dir = "../yaml2raml/out"
@dir_root = "java_output"
OptionParser.new do |parser|
     parser.on('-f', '--from=DIR') do |dir|
       p dir
       @root_dir = dir
     end
     parser.on('-t', '--to=DIR') do |dir|
          p dir
          @dir_root = dir
          end
end.parse!
@dir_resource = "#{@dir_root}/src/main/resources"
@template_dir = "java_template"
@raml = YAML.load_file("#{@root_dir}/api.raml")
group = @raml["(project)"]["group"]
artifact = @raml["(project)"]["artifact"]
p @raml["(project)"]
@project = artifact
package = "#{group}.#{@project}"
@dir_main = "#{@dir_root}/src/main/java/#{package.gsub('.','/')}"
@script_db = {}
p @dir_main

FileUtils.remove_dir(@dir_root,true) unless !Dir.exists?(@dir_root)
FileUtils.mkdir_p(@dir_root)
FileUtils.mkdir_p(@dir_main) unless Dir.exists?(@dir_main)
FileUtils.mkdir_p(@dir_resource) unless Dir.exists?(@dir_resource)

title = @raml["title"]
@version = @raml["version"]
@media_type = @raml["mediaType"]

@dir_model = "#{@dir_main}/model"
@dir_controller =  "#{@dir_main}/controller"
@dir_config =  "#{@dir_main}/config"
@dir_bean =  "#{@dir_main}/bean"
@dir_repository =  "#{@dir_main}/repository"
@dir_repository_impl =  "#{@dir_main}/repository/impl"
@dir_converter =  "#{@dir_main}/converter"
@dir_service =  "#{@dir_main}/service"
@dir_service_impl =  "#{@dir_main}/service/impl"

FileUtils.mkdir_p(@dir_model) unless Dir.exists?(@dir_model)
FileUtils.mkdir_p(@dir_controller) unless Dir.exists?(@dir_controller)
FileUtils.mkdir_p(@dir_config) unless Dir.exists?(@dir_config)
FileUtils.mkdir_p(@dir_bean) unless Dir.exists?(@dir_bean)
FileUtils.mkdir_p(@dir_repository) unless Dir.exists?(@dir_repository)
FileUtils.mkdir_p(@dir_repository_impl) unless Dir.exists?(@dir_repository_impl)
FileUtils.mkdir_p(@dir_converter) unless Dir.exists?(@dir_converter)
FileUtils.mkdir_p(@dir_service) unless Dir.exists?(@dir_service)
FileUtils.mkdir_p(@dir_service_impl) unless Dir.exists?(@dir_service_impl)

FileUtils.cp("#{@template_dir}/resources/application.properties","#{@dir_resource}/application.properties")

def calcular_modelos dir
     models_file = YAML.load_file("#{dir.sub(".",@root_dir)}")
     models = {}
     models_enums ={}
     models_file["types"].each do |key, value|
          models[key] = value["properties"] unless value["properties"].nil?
     end
      models.each do |key, value|
          attributos  = {}
          attributos = calcular_attributos(value)
          generar_javaModel(key,attributos) 
      end
      models_file["types"].each do |key, value|
          models_enums[key] = value["enum"] unless value["enum"].nil?
     end 
     models_enums.each do |key, value|
          attributos  = {}
      end
end

def calcular_attributos value_hash
     attributos = {}
     value_hash.each do |key, value|
          if value["type"].nil?
               attributos[key.gsub("?","")] = {type:value}
          else
               attributos[key.gsub("?","")] = obtener_attributo(value["type"])  
          end
     end
     attributos
end
def get_propertyType file , type_raw ,decimal 
     if type_raw=="number" && decimal
          type = "BigDecimal"
     elsif  type_raw=="number"
          type = "Long"
     elsif  type_raw=="integer"
          type = "Integer"
     elsif  type_raw=="string"
          type = "String"
     elsif  type_raw.start_with?"time"
          type = "Time"
     elsif  type_raw.start_with?"date"
          type = "Date"
     else  
          if type_raw.include?"."
               name_file = type_raw.split(".")[0]
               type_raw = type_raw.split(".")[1]
               attribute_dir =  Dir["#{@root_dir}/**/#{name_file.underscore}*"]
               file =  YAML.load_file (attribute_dir[0])
          end
          next_type = file["types"][type_raw.gsub("[]","")]["type"]
          is_multipleOf = file["types"][type_raw.gsub("[]","")]["multipleOf"]

          if !next_type.nil? && next_type!="object"
               type = get_propertyType file, next_type, is_multipleOf
          else
               if type_raw.include?"[]"
                    type = "List<#{type_raw.gsub("[]","") }>"
               else
                    type = type_raw 
               end
          end

     end
     type
end

def obtener_attributo file

     if file.include?(".")
          name_file = file.split(".")[0]
          name_attr = file.split(".")[1].gsub("[]","")
          attribute_dir =  Dir["#{@root_dir}/**/#{name_file}*"]
          attribute_raml =  YAML.load_file(attribute_dir[0])
          if attribute_raml["types"][name_attr]["type"].nil?
               attribute = {type:"#{name_attr}Model"}
          else
          attribute_type = get_propertyType  attribute_raml,attribute_raml["types"][name_attr]["type"], attribute_raml["types"][name_attr]["multipleOf"]       
          attribute = {type:attribute_type}
          end
     else
          if file.start_with?"date"
               attribute = {type: "Date" }
          else
               attribute = {type: "#{file}Model" }
          end
     end
     attribute

end

def generar_javaModel name, attributes
  #   p "generando archivo: #{name} "
     @name = name
     @attributes = attributes
     model_template = "#{@template_dir}/main/model/ModelTemplate.erb"
     templateFile = File.open(model_template)
     templateContent  = templateFile.read
     renderedTemplate = ERB.new(templateContent, nil, '-')         
     File.open( "#{@dir_model}/#{name}Model.java" , 'w+')  { |f| f.write(renderedTemplate.result()) }
end

def generar_PostmanColletion
        model_template = "#{@template_dir}/templateCollections.postman_collection.erb"
        templateFile = File.open(model_template)
        templateContent  = templateFile.read
        renderedTemplate = ERB.new(templateContent, nil, '-')         
        File.open( "#{@project}.postman_collection.json" , 'w+')  { |f| f.write(renderedTemplate.result()) }
end

def generar_pom
   #  p "generando archivo: pom.xml"
     model_template = "#{@template_dir}/pom.erb"
     templateFile = File.open(model_template)
     templateContent  = templateFile.read
     renderedTemplate = ERB.new(templateContent, nil, '-')         
     File.open( "#{@dir_root}/pom.xml" , 'w+')  { |f| f.write(renderedTemplate.result()) }
end
def generar_javaConfig
   #  p "generando archivo: WebConfig.java"
     model_template = "#{@template_dir}/main/config/WebConfig.erb"
     templateFile = File.open(model_template)
     templateContent  = templateFile.read
     renderedTemplate = ERB.new(templateContent, nil, '-')         
     File.open( "#{@dir_config}/WebConfig.java" , 'w+')  { |f| f.write(renderedTemplate.result()) }
end
def generar_javaModels
     files_uses = @raml["uses"] 
     files_uses.each do |name , dir|  
          @name = name
          if dir.include?("types") && name != "base"    
               calcular_modelos dir  
          end
     end
end


def generar_javaController domains
     domains.each do |domain, props|
          @controller = props
          @name = "#{domain.gsub("/","").singularize.camelize}"         
          model_template = "#{@template_dir}/main/controller/ControllerTemplate.erb"
          templateFile = File.open(model_template)
          templateContent  = templateFile.read
          renderedTemplate = ERB.new(templateContent, nil, '-')         
          File.open( "#{@dir_controller}/#{@name}Controller.java" , 'w+')  { |f| f.write(renderedTemplate.result()) }
     end
end

def generar_javaApp
    # p "generando archivo: ApplicationTemplate "
     model_template = "#{@template_dir}/main/ApplicationTemplate.erb"
     templateFile = File.open(model_template)
     templateContent  = templateFile.read
     renderedTemplate = ERB.new(templateContent, nil, '-')         
     File.open( "#{@dir_main}/#{@project.camelize}Application.java" , 'w+')  { |f| f.write(renderedTemplate.result()) }
end
def generar_javaAppInit
    # p "generando archivo: AppInitializerTemplate "
     model_template = "#{@template_dir}/main/AppInitializerTemplate.erb"
     templateFile = File.open(model_template)
     templateContent  = templateFile.read
     renderedTemplate = ERB.new(templateContent, nil, '-')         
     File.open( "#{@dir_main}/AppInitializer.java" , 'w+')  { |f| f.write(renderedTemplate.result()) }
end

def procesar_dominio domain , resource, domains
     p domain
     entity = resource["(table)"]["name"] unless resource["(table)"].nil?
     p entity
     if !entity.nil?
          @entities[entity] = {} if @entities[entity].nil? 
          @entities[entity][:entity_to_model] = [] if @entities[entity][:entity_to_model].nil? 
          @entities[entity][:model_to_entity] = [] if @entities[entity][:model_to_entity].nil? 
     end
     domains[domain] = {}
     domains[domain]["props"] = {entity:entity.split(".").last}
     resource.each do |key, value|
          if key == "get" || key == "post" || key == "put" || key == "delete"
               if value["responses"][200]
                    code = "ok"
                    object_file = value["responses"][200]["body"][@media_type]["type"] unless  value["responses"][200]["body"].nil?
                    object = object_file.split(".")[1] unless  value["responses"][200]["body"].nil?

               elsif value["responses"][201]
                    code = "created"
                    object_file = value["responses"][201]["body"][@media_type]["type"] unless  value["responses"][201]["body"].nil?
                    object = object_file.split(".")[1] unless  value["responses"][201]["body"].nil?

               elsif value["responses"][202]
                    code = "accepted"
                    object_file = value["responses"][202]["body"][@media_type]["type"] unless  value["responses"][202]["body"].nil?
                    object = object_file.split(".")[1] unless  value["responses"][202]["body"].nil?
               end   
               domain_to_analyze = domain
               path = []
               until domain_to_analyze.str_between("{","}").nil?
                    path_string = domain_to_analyze.str_between("{","}")
                    path.push path_string
                    domain_to_analyze = domain_to_analyze.gsub("{#{path_string}}","") 
               end
               body_file = value["body"][@media_type]["type"] unless value["body"].nil?
               body = body_file.split(".")[1] unless value["body"].nil?
               domains[domain][key] = { request:{ path:path ,body:body } , response: {code: code, object: object}}
               if !entity.nil?
                    @entities[entity][:entity_to_model].push object_file unless  @entities[entity][:entity_to_model].include? object_file if !object_file.nil?
                    @entities[entity][:model_to_entity].push body_file unless  @entities[entity][:model_to_entity].include?body_file if !body_file.nil?
               end
          end    
     end 
     resources = resource.select {|property| property.start_with?"/" }
     resources.each do |domain_recursive, resource_recursive|
          domain += domain_recursive
          domains = procesar_dominio domain,resource_recursive, domains
     end 
     domains
end
def cargar_dominios
     @resourses = @raml.select {|property| property.start_with?"/" }
     @entities = {}
     @domains = {}
     @entity_to_model ={}
     @model_to_entity ={}
     @resourses.select do |domain, resource|
          entity = resource["(table)"] unless resource["(table)"].nil?
          domain_aux = domain.sub("/","") if domain.start_with?("/")        
          domain_array = domain_aux.split("/")
          service_name = ""
          domain_array.each do |item|
               item_aux = item.gsub("{","").gsub("}","")
               service_name += item_aux.camelize
          end
          @domains[domain] = {}
          @domains[domain] = procesar_dominio domain,resource,@domains[domain]
     end
     generar_javaEntity @entities
     generar_javaController @domains
end


def generar_javaService name
          @service_name = name
          @domains.select{|key| key.include?name.pluralize.downcase}.each do |domain,props| 
          
               @service_props = props
               service_template = "#{@template_dir}/main/service/TemplateService.erb"
               templateFile = File.open(service_template)
               templateContent  = templateFile.read
               renderedTemplate = ERB.new(templateContent, nil, '-')         
               File.open( "#{@dir_service}/#{@service_name}Service.java" , 'w+')  { |f| f.write(renderedTemplate.result()) }
               generar_javaServiceImpl @service_name
          end
end
def generar_javaServiceImpl name
          service_template = "#{@template_dir}/main/service/impl/TemplateServiceImpl.erb"
          templateFile = File.open(service_template)
          templateContent  = templateFile.read
          renderedTemplate = ERB.new(templateContent, nil, '-')         
          File.open( "#{@dir_service_impl}/#{name}ServiceImpl.java" , 'w+')  { |f| f.write(renderedTemplate.result()) }
end

def is_pk reference
     attribute_dir =  Dir["#{@root_dir}/**/base.raml*"]
     attribute_raml =  YAML.load_file(attribute_dir[0])

     attribute_raml["types"][reference["table"].camelize]["properties"][reference["column"]]["(pk)"]
end
def generar_javaEntity entities
     entities.each do |name,props|
          name_file = name.split(".")[0]
          @name_attr = name.split(".")[1]
          attribute_dir =  Dir["#{@root_dir}/**/#{name_file.underscore}*"]
          attribute_raml =  YAML.load_file(attribute_dir[0])
          @entity_insert = {}
          @entity_struc = {}
          @entity_join =  {}
          @pk = []
          @identity = ""
          attribute_raml["types"][@name_attr]["properties"].each do |property, value|
               if value["(pk)"]
                    @pk.push property
                    @identity = property if value["(pk)"]["identity"]
               end
               type_raw = value["type"]
               type = get_propertyType attribute_raml, type_raw,value["multipleOf"]
               property = property.gsub("?","")
               if value["(fk)"]
                    @entity_join[value["(fk)"]["table"]] = [] if  @entity_join[value["(fk)"]["table"]].nil?
                    @entity_join[value["(fk)"]["table"]].push({field:value["(fk)"], match:"#{@name_attr.underscore}.#{property}"})
                    @entity_insert[property] = { name: property.camelize, type: type } if is_pk(value["(fk)"])
               else
                    @entity_insert[property] = { name: property.camelize, type: type } 
               end     
               @entity_struc[property] = { name: property.camelize, type: type } 
          end
          fields = ""
          @entity_struc.each_with_index do |(key, type),index|
                    fields = fields + "#{@name_attr.underscore}.#{key.to_s} as #{key.to_s} "
                    if index != @entity_struc.size - 1
                         fields = fields + ", "
                    else
                         fields = fields + " "
                    end
          end 
          join=""
          @entity_join.each do |table,props|
               join = join + " left join #{table.underscore}"
               props.each do |prop|
                    fields = fields.gsub("#{prop[:match]} as","#{prop[:field]["table"]}.#{prop[:field]['column']} as")
                   join = join + " on #{prop[:match]}=#{table.underscore}.#{prop[:field]['column']}" if  is_pk(prop[:field])
               end

          end
          @fields = fields
          @joins = join

          @script_db[@name_attr]={struc:@entity_struc,identity:@identity,pk:@pk}
          entity_template = "#{@template_dir}/main/bean/EntityTemplate.erb"
          templateFile = File.open(entity_template)
          templateContent  = templateFile.read
          renderedTemplate = ERB.new(templateContent, nil, '-')         
          File.open( "#{@dir_bean}/#{@name_attr}Bean.java" , 'w+')  { |f| f.write(renderedTemplate.result()) }
          generar_javaRepository @name_attr
          generar_javaRepositoryImpl @name_attr
          generar_javaConverter @name_attr, props[:entity_to_model], props[:model_to_entity]
          generar_javaService @name_attr

     end
end
def generar_javaRepository repository_name
     @repository_name = repository_name
     entity_template = "#{@template_dir}/main/repository/EntityTemplateRepository.erb"
     templateFile = File.open(entity_template)
     templateContent  = templateFile.read
     renderedTemplate = ERB.new(templateContent, nil, '-')         
     File.open( "#{@dir_repository}/#{@repository_name}Repository.java" , 'w+')  { |f| f.write(renderedTemplate.result()) }
end
def generar_javaRepositoryImpl repository_name
     @repository_name = repository_name
     entity_template = "#{@template_dir}/main/repository/impl/TemplateRepositoryImpl.erb"
     templateFile = File.open(entity_template)
     templateContent  = templateFile.read
     renderedTemplate = ERB.new(templateContent, nil, '-')         
     File.open( "#{@dir_repository_impl}/#{@repository_name}RepositoryImpl.java" , 'w+')  { |f| f.write(renderedTemplate.result()) }
end
def generar_javaConverter converter_name, entity_to_model, model_to_entity

     @entity_to_model_struc = {}
     entity_to_model.each do |model_file|
          name_file = model_file.split(".")[0]
          name_model = model_file.split(".")[1]
          attribute_dir =  @raml["uses"][name_file].sub(".", @root_dir)
          attribute_raml =  YAML.load_file(attribute_dir)
          attribute_raml["types"][name_model]["properties"].each do |property, value|
               name = value["type"].gsub("[]","")
               @entity_to_model_struc[name] = {}
                attribute_raml["types"][name]["properties"].each do |property, value|
                    type = value["type"].split(".").last.underscore
                    type = (type.camelize.gsub(converter_name.camelize,"")).underscore
                    @entity_to_model_struc[name][property] = { name: property.camelize.gsub("?",""), type: @entity_struc[type][:name] }  unless @entity_struc[type].nil?
               end
          end

     end
     @model_to_entity_struc = {}
     model_to_entity.each do |model_file|
          name_file = model_file.split(".")[0]
          name_model = model_file.split(".")[1]
          attribute_dir =  @raml["uses"][name_file].sub(".", @root_dir)
          attribute_raml =  YAML.load_file(attribute_dir)

          attribute_raml["types"][name_model]["properties"].each do |property, value|
               name = value["type"].gsub("[]","")
               @model_to_entity_struc[name] = {}
               
               attribute_raml["types"][name]["properties"].each do |property, value|
                    type = value["type"].split(".").last.underscore
                    type = (type.camelize.gsub(converter_name.camelize,"")).underscore
                    @model_to_entity_struc[name][property] = { name: property.camelize.gsub("?",""), type: @entity_struc[type][:name] } unless @entity_struc[type].nil? 
               end
          end
          
     end
     @converter_name = converter_name
     model_template = "#{@template_dir}/main/converter/ConverterTemplate.erb"
     templateFile = File.open(model_template)
     templateContent  = templateFile.read
     renderedTemplate = ERB.new(templateContent, nil, '-')         
     File.open( "#{@dir_converter}/#{@converter_name}Converter.java" , 'w+')  { |f| f.write(renderedTemplate.result()) }
 end
cargar_dominios
generar_pom
generar_javaConfig
generar_javaAppInit
generar_javaModels
generar_javaApp
generar_PostmanColletion

 
