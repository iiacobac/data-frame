# data-frame

For running
in set directory in data-frame/src/main/java/

javac co/ykk/dataframe/QS.java


 ((cat ../resources/example-files_payroll-2016.csv ../resources/example-files_bonuses-2016.csv | java co.ykk.dataframe.QS +) ; (cat ../resources/example-files_payroll-2017.csv ../resources/example-files_bonuses-2017.csv | java co.ykk.dataframe.QS +)) | java co.ykk.dataframe.QS +
