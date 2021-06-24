import logging
import os
from enum import Enum
import json

#https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol

class Exceptions:
    class NoPropertiesException(Exception):
        pass
    class LogFileAbsenceException(Exception):
        pass
    class WrongPropertyException(Exception):
        pass

#enums to easily choose needed option in configuration
class Enums:
    class HTTPRequest(Enum):
        GET = 1
        POST = 2
        PUT = 3
        DELETE = 4
        TRACE = 5
        HEAD = 6
        OPTIONS = 7
        PATCH = 8
        CONNECT = 9
    class LoggingLevels(Enum):
            NOTSET = 0
            DEBUG = 10
            INFO = 20
            WARNING = 30
            ERROR = 40
            CRITICAL = 50
            
class Config:
    fileName="configuration.json"
    config = dict();

    def __init__(self):
        self.init_config()

    def init_config(self):
        self.config['log_filename'] = input("Enter the name of webserver log: ")
        self.config['request_method'] = input("Enter Name of one of the HTTP request methods("+str([f"{e.name}" for e in Enums.HTTPRequest])+"): ")
        self.config['logging_level'] = input("Enter Logging level("+str([f"{e.name}" for e in Enums.LoggingLevels])+"): ")
        self.config['displayed_at_once'] = input("Enter number of log lines to be displayed at once: ")
        self.config['ignore_code'] = input("Enter which status code parser should ignore: ")

        with open(self.fileName, "w") as write:
                json.dump(self.config, write)
        return self.config



        


class ConfigReader:
    config = dict();
    logs = dict();
    def __init__(self,config_path):
        try:
            with open(config_path, "r") as config:
                self.config = json.load(config)
                self.config_validation()
        except FileNotFoundError as err:
            print("Config file does not exist")
            quit(1)
        except json.JSONDecodeError as err:
            print("Config file doesn't exist or not in json format")
            print(err.msg)
            quit(1)
        except Exceptions.NoPropertiesException as err:
            print(err)
            quit(1)
        except Exceptions.WrongPropertyException as err:
            print(err)
            quit(1)
        except Exceptions.LogFileAbsenceException as err:
            print(err)
            quit(1)
        print("READY TO PARSE!!!")
        logging.basicConfig(level=Enums.LoggingLevels[self.config["logging_level"]].value)
        self.logs = self.read_log()

    def config_validation(self):
        #1. check if we have all needed config properties
        if not ("log_filename" in self.config and "request_method" in self.config and "logging_level" in self.config and "displayed_at_once" in self.config):
            raise Exceptions.NoPropertiesException("Config doesn't contain required properties")
        #2. check if needed properties in correct format (e.g. HTTP methods should be GET, POST etc.)
        if self.config["request_method"] not in Enums.HTTPRequest.__members__:
                raise Exceptions.WrongPropertyException("Request method "+self.config["request_method"]+" doesn't exist")
        if self.config["logging_level"] not in Enums.LoggingLevels.__members__:
                raise Exceptions.WrongPropertyException("Logging level "+self.config["logging_level"]+" doesn't exist")
        #3. check if log file exists
        if not os.path.exists(self.config["log_filename"]):
            raise Exceptions.LogFileAbsenceException("Log file "+str(self.config["log_filename"]+" doesn't exist"))
    

    def read_log(self):
        logging.debug("STARTED READING LOGS FROM "+self.config["log_filename"])
        dictionary = dict();
        with open(self.config["log_filename"]) as logs:
            counter = 0;
            for log in logs:
                split = log.split()
                info = {
                    "ip": str(split[0]),    
                    "date": str(split[3][1:-1]),
                    "req": {
                        "type": split[5][1:],
                         "resource": split[6],
                         "protocol": split[7][:-1]
                         },
                     "code":split[8]
                }
                if info["ip"] in dictionary: # check if we already have a key with that ip
                  # appending to dictionary everything except ip
                  dictionary.get(info["ip"]).append(info)
                else:
                    # appending to dictionary everything except ip 
                     dictionary[info["ip"]] = [info]
                counter+=1
        logging.info(f"SUCCESFULLY READED FILE")
        logging.info(f"{len(dictionary.keys())} ADDRESSES MADE {counter} REQUESTS TO THE SERVER")
        return dictionary

    def print_index_html(self):
        index = dict()
        counter = 0
        logging.debug("STARTED READING INDEX.HTML FROM LOGS")

        for key,val in self.logs.items():
            for entry in val:
                if "index.html" in entry["req"]['resource']:
                    counter+=1
                    obj= {"req": {"type": entry["req"]["type"], "resource": entry["req"]["resource"]}}
                    if key not in index:
                        index[key] = [obj]
                    else:
                        index[key].append(obj)
        logging.info(f"THERE ARE {counter} 'index.html' REQUESTS")
        self.print_logs(index)

    def print_logs(self,logs:dict):
        displayed = 0
        counter = 0
        req_type = self.config["request_method"]
        logging.debug("STARTED PRINTING FILTERED BY CONFIGURATION REQUESTS")
        for key,val in logs.items():
                for entry in val:
                    if displayed < int(self.config["displayed_at_once"]) :
                        if entry["req"]["type"] == self.config["request_method"]:
                            print(key,entry)
                            counter+=1
                            displayed+=1
                    else:
                        os.system("pause")
                        displayed=0
        logging.info(f"{counter} REQUESTS WERE PRINTED")
        
    def print_logs_ignoring_code(self,logs:dict):
        displayed = 0
        counter = 1
        req_type = self.config["request_method"]
        logging.debug("STARTED PRINTING FILTERED BY CONFIGURATION REQUESTS")
        for key,val in logs.items():
                for entry in val:
                    if displayed < int(self.config["displayed_at_once"]) :
                        if entry["req"]["type"] == self.config["request_method"] and entry["code"] != self.config["ignore_code"]:
                            print(key,entry)
                            displayed+=1
                            counter +=1
                    else:
                        os.system("pause")
                        displayed=0
        logging.info(f"{counter} REQUESTS WERE PRINTED")
        
def run():
      reader = Config()
    #  configReader = ConfigReader("configuration.json")
    #  configReader.print_index_html()
     
     configReader.print_logs(configReader.logs)
    #  configReader.print_logs_ignoring_code(configReader.logs)


if __name__ == "__main__":
    run()