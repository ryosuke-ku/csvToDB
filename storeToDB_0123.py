lsimport logging.config
from ast.ast_processor_Production import AstProcessorProduction
from ast.ast_processor_Production_line import AstProcessorProductionLine
from ast.ast_processor_Test_line import AstProcessorTestLine
from ast.ast_processor_Test import AstProcessorTest
from ast.ast_processor_TestMethodCall import AstProcessorTestMethodCall
from ast.basic_info_listener_pt import BasicInfoListener
import glob
import re
import os
from collections import defaultdict
import xlwt
import csv
from pymongo import MongoClient
from datetime import datetime


class rdict(dict):
    def __getitem__(self, key):
        try:
            return super(rdict, self).__getitem__(key)
        except:
            try:
                ret=[]
                for i in self.keys():
                    m= re.match("^"+key+"$",i)
                    if m:ret.append( super(rdict, self).__getitem__(m.group(0)) )
            except:raise(KeyError(key))
        return ret

def printProductionPath(project_name):
    initTestPath1 = glob.glob('D:\\ryosuke-ku\\data_set\\Git_20161108\\' + project_name + '\\**\\*Test.java', recursive=True)
    productionPath1 =[]
    for initTestPath in initTestPath1:
        testProjectName = re.sub(r"D:\\ryosuke-ku\\data_set\\Git_20161108\\", "", initTestPath)[re.sub(r"D:\\ryosuke-ku\\data_set\\Git_20161108\\", "", initTestPath).find("\\") + 1:][:re.sub(r"D:\\ryosuke-ku\\data_set\\Git_20161108\\", "", initTestPath)[re.sub(r"D:\\ryosuke-ku\\data_set\\Git_20161108\\", "", initTestPath).find("\\") + 1:].find('\\')]
        # print(testProjectName)
        testFileName = initTestPath[initTestPath.rfind("\\") + 1:]
        fileName = testFileName.replace('Test','').replace('test','')
        prodPath1 = glob.glob('D:\\ryosuke-ku\\data_set\\Git_20161108\\' + project_name + '\\' + testProjectName + '\\**\\' + fileName , recursive=True)
        if len(prodPath1)==0:
            pass
        else:
            productionPath1.append(prodPath1[0].replace('\\','/'))
    return productionPath1


def printTestPath(project_name):
    initTestPath1 = glob.glob('D:\\ryosuke-ku\\data_set\\Git_20161108\\' + project_name + '\\**\\*Test.java', recursive=True)
    testPath1 = []
    for initTestPath in initTestPath1:
        testProjectName = re.sub(r"D:\\ryosuke-ku\\data_set\\Git_20161108\\", "", initTestPath)[re.sub(r"D:\\ryosuke-ku\\data_set\\Git_20161108\\", "", initTestPath).find("\\") + 1:][:re.sub(r"D:\\ryosuke-ku\\data_set\\Git_20161108\\", "", initTestPath)[re.sub(r"D:\\ryosuke-ku\\data_set\\Git_20161108\\", "", initTestPath).find("\\") + 1:].find('\\')]
        # print(testProjectName)
        testFileName = initTestPath[initTestPath.rfind("\\") + 1:]
        fileName = testFileName.replace('Test','').replace('test','')
        prodPath1 = glob.glob('D:\\ryosuke-ku\\data_set\\Git_20161108\\' + project_name + '\\' + testProjectName + '\\**\\' + fileName , recursive=True)
        if len(prodPath1)==0:
            pass
        else:
            testPath1.append(initTestPath.replace('\\','/'))
    return testPath1

def makeFolder():
    os.mkdir('projects')
    projects_array = ['ABCD','ABCD','EFGH','IJKL','MNOP','QRST','UVW','XYZ']
    # projects_array = ['ABCD']
    for project in projects_array:
        data_set = glob.glob('D:\\ryosuke-ku\\data_set\\Git_20161108\\' + project + '\\*')
        # print(data_set_ABCD)
        os.mkdir('projects\\' + project)
        for project_data in data_set:
            num_slash = project_data.rfind("\\")
            project_folder = project_data[num_slash + 1:]
            print(project_folder)
            os.mkdir('projects\\' + project + '\\' + project_folder)
            os.mkdir('projects\\' + project + '\\' + project_folder + '\\main')
            # os.mkdir('projects\\' + project + '\\' + project_folder + '\\test')

def delete_emptyProjects():
    try:
        os.rmdir('projects\\ABCD\\')
    except OSError as e:
        print('catch OSError:', e)
        pass

def testMethodMapCall(Path):
    Testmethodcalls_list = AstProcessorTestMethodCall(None, BasicInfoListener()).execute(Path) #target_file_path(テストファイル)内のメソッド名をすべて取得
    testMethodMapcall = defaultdict(list)
    # num = 0
    for i in Testmethodcalls_list:
        for j in Testmethodcalls_list[i]:
            if len(j) == 0:
                pass
            else:
                testMethodMapcall[j] = i
    
    return testMethodMapcall


if __name__ == '__main__':
    project = '0123'

    num_IndexError = 0
    num_UnicodeEncodeError = 0
    num_UnicodeDecodeError = 0
    num_RecursionError = 0
    num_FileNotFoundError = 0

    clint = MongoClient()
    db = clint['testMapList_0123']

    data_set = glob.glob('D:\\ryosuke-ku\\data_set\\Git_20161108\\' + project + '\\*')

    csv_file = open("Output_TestSmellDetection_0123.csv", "r", encoding="ms932", errors="", newline="" )
    f = csv.reader(csv_file, delimiter=",", doublequote=True, lineterminator="\r\n", quotechar='"', skipinitialspace=True)
    pathToTestSmells = defaultdict(list)
    header = next(f)
    print(header)

    for row in f:
        testSmellArray = []
        for smell_num in range(6,27):
            testSmellArray.append(row[smell_num])
            
            project_path = row[3].replace('\\','/').replace("D:/ryosuke-ku/data_set/Git_20161108/0123/","")
        # print('project_name : ' + project_path)
        # print(testSmellArray)
        pathToTestSmells[project_path] = testSmellArray
    print(pathToTestSmells)

    for project_data in data_set:
        project_folder = project_data[project_data.rfind("\\") + 1:]
        project_name = str(project) + '/' + project_folder
        PPath = printProductionPath(project_name)
        TPath = printTestPath(project_name)

        num_projects = int(len(PPath))

        if len(PPath) != 0 and len(TPath) != 0:
            try:
                # print(project_folder)
                for num_path in range(num_projects):

                    Productionmethods_list = AstProcessorProduction(None, BasicInfoListener()).execute(PPath[num_path]) #プロダクションファイル内のメソッド名をすべて取得
                    ProductionmethodLine_list = AstProcessorProductionLine(None, BasicInfoListener()).execute(PPath[num_path]) #プロダクションファイル内のメソッド名をすべて取得
                    TestmethodLine_list = AstProcessorTestLine(None, BasicInfoListener()).execute(TPath[num_path]) #プロダクションファイル内のメソッド名をすべて取得

                    PPath_last = PPath[num_path].replace("D:/ryosuke-ku/data_set/Git_20161108/" + project + "/","")
                    TPath_last = TPath[num_path].replace("D:/ryosuke-ku/data_set/Git_20161108/" + project + "/","")
        
                    print(PPath_last)
                    print(TPath_last)
                    testDict = testMethodMapCall(TPath[num_path])
                    rd = rdict(testDict)

                    path_dir = PPath_last[:PPath_last.rfind('/')+1]
                    file_name = PPath_last[PPath_last.rfind('/')+1:][:PPath_last[PPath_last.rfind('/')+1:].rfind('.')]

                    f = open(PPath[num_path], "r", encoding="utf-8")
                    lines = f.readlines() # 1行毎にファイル終端まで全て読む(改行文字も含まれる)
                    f.close()

                    os.makedirs('systems/' + path_dir, exist_ok=True)
                    file = open('systems/' + path_dir + file_name + '.java', "w")

                    for line in range(len(lines)):
                        if line == 0:
                            file.write('public class ' + file_name.capitalize() + ' {\n')
                        elif line == len(lines)-1:
                            file.write('}\n')
                        else:
                            file.write('\n')

                    file.close()

                    # print(pathToTestSmells[PPath_last])
                    print('pathToTestSmells : ' + str(len(pathToTestSmells[PPath_last])))

                    nort = 0
                    for ProductionMethod in Productionmethods_list:
                        print(ProductionMethod)
                        startline = int(ProductionmethodLine_list[ProductionMethod][0])-1
                        endline = int(ProductionmethodLine_list[ProductionMethod][1])

                        PMethod = ProductionMethod[:ProductionMethod.find('_')]

    
                        remethods = rd["^(?=.*" + PMethod + ").*$"]
                        rts = list(set(remethods))
                        print(rts)
                        
                        if len(remethods) == 0:
                            nort += 1

                        else:
                            file = open('systems/' + path_dir + file_name + '.java', "r", encoding="utf-8")
                            file_lines = file.readlines() # 1行毎にファイル終端まで全て読む(改行文字も含まれる)

                            for row in range(len(file_lines)):
                                # print(row)
                                if row >= startline and row <= endline:
                                    file_lines[row] = lines[row].replace('\n', '') + '\n'
                            file.close()
                            # print(file_lines)

                            with open('systems/' + path_dir + file_name + '.java', 'w', encoding="utf-8") as f:
                                for file_line in file_lines:
                                    f.write(file_line) 

                            for rt in rts:
                                startline_test = int(TestmethodLine_list[rt][0])-1
                                endline_test = int(TestmethodLine_list[rt][1])

                                testsmells = pathToTestSmells[PPath_last]
                                Assertion_Roulette = testsmells[0]
                                Conditional_Test_Logic = testsmells[1]
                                Constructor_Initialization = testsmells[2]
                                Default_Test = testsmells[3]
                                Empty_Test = testsmells[4]
                                Exception_Catchingowing = testsmells[5]
                                General_Fixture = testsmells[6]
                                Mystery_Guest = testsmells[7]
                                Print_Statement = testsmells[8]
                                Redundant_Assertion = testsmells[9]
                                Sensitive_Equality = testsmells[10]
                                Verbose_Test = testsmells[11]
                                Sleepy_Test = testsmells[12]
                                Eager_Test = testsmells[13]
                                Lazy_Test = testsmells[14]
                                Duplicate_Assert = testsmells[15]
                                Unknown_Test = testsmells[16]
                                Ignored_Test = testsmells[17]
                                Resource_Optimism = testsmells[18]
                                Magic_Number_Test = testsmells[19]
                                Dependent_Test = testsmells[20]

                                post = {
                                    'path': PPath_last,
                                    'startline1': startline,
                                    'endline1': endline,
                                    'testpath': TPath_last,
                                    'startline2': startline_test,
                                    'endline2': endline_test,
                                    'Assertion Roulette':Assertion_Roulette,
                                    'Conditional Test Logic':Conditional_Test_Logic,
                                    'Constructor Initialization':Constructor_Initialization,
                                    'Default Test':Default_Test,
                                    'EmptyTest':Empty_Test,
                                    'Exception Catchingowing':Exception_Catchingowing,
                                    'General Fixture':General_Fixture,
                                    'Mystery Guest':Mystery_Guest,
                                    'Print Statement':Print_Statement,
                                    'Redundant Assertion':Redundant_Assertion,
                                    'Sensitive Equality':Sensitive_Equality,
                                    'Verbose Test':Verbose_Test,
                                    'Sleepy Test':Sleepy_Test,
                                    'Eager Test':Eager_Test,
                                    'Lazy Test':Lazy_Test,
                                    'Duplicate Assert':Duplicate_Assert,
                                    'Unknown Test':Unknown_Test,
                                    'IgnoredTest':Ignored_Test,
                                    'Resource Optimism':Resource_Optimism,
                                    'Magic Number Test':Magic_Number_Test,
                                    'Dependent Test':Dependent_Test,
                                }
                                db.mappingCollection_0123.insert_one(post)


                        if nort == len(Productionmethods_list):
                            os.remove('systems/' + path_dir + file_name + '.java')

            except IndexError as e:
                print('catch IndexError:', e)
                num_IndexError += 1
            except UnicodeEncodeError as e:
                print('catch UnicodeEncodeError:', e)
                num_UnicodeEncodeError += 1
            except UnicodeDecodeError as e:
                print('catch UnicodeDecodeError:', e)
                num_UnicodeDecodeError += 1
            except RecursionError as e:
                print('catch RecursionError:', e)
                num_RecursionError += 1
            except FileNotFoundError as e:
                print('catch FileNotFoundError:', e)
                num_FileNotFoundError += 1

                        
                     


