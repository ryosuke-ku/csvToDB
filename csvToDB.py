import csv
from collections import defaultdict

csv_file = open("Output_TestSmellDetection_0123.csv", "r", encoding="ms932", errors="", newline="" )
#リスト形式
f = csv.reader(csv_file, delimiter=",", doublequote=True, lineterminator="\r\n", quotechar='"', skipinitialspace=True)
#辞書形式
# f = csv.DictReader(csv_file, delimiter=",", doublequote=True, lineterminator="\r\n", quotechar='"', skipinitialspace=True)
pathToTestSmells = defaultdict(list)


header = next(f)
print(header)
for row in f:
    testSmellArray = []
    for smell_num in range(6,27):
        testSmellArray.append(row[smell_num])
    # print(testSmellArray)
    project_path = row[2].replace("D:\\ryosuke-ku\\data_set\\Git_20161108\\0123\\","")
    print('project_name : ' + project_path)
    pathToTestSmells[project_path] = testSmellArray

print(pathToTestSmells['360-Innovations_VaadinSmartGWT\\VaadinSmartGWT\\org.vaadin.smartgwt\\src\\test\\java\\org\\vaadin\\smartgwt\\server\\form\\fields\\FormItemIconTest.java'])
