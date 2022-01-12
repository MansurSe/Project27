import comparator.StudentComparator;
import comparator.UniversityComparator;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

import comparator.UniversityComparator;
import enums.StudentComparatorType;
import enums.UniversityComparatorType;
import model.Statistics;
import model.Student;
import model.University;
import util.ComparatorUtil;
import util.JsonUtil;
import util.StatisticsUtil;


public class Main {
    public static void main(String[] args) throws IOException {

        List<University> universities = ReadFromExcel.readFromFileUniversity("universityInfo.xls");

        UniversityComparator universityComparator = ComparatorUtil.getUniversityComparator(UniversityComparatorType.YEAR);
       universities.sort(universityComparator);
        String universitiesJson = JsonUtil.universityListToJson(universities);
        // проверяем, что json создан успешно 
        System.out.println(universitiesJson);

        List<University> universitiesFromJson = JsonUtil.jsonToUniversityList(universitiesJson);
        // проверяем, что обратно коллекция воссоздаётся в таком же количестве элементов
        System.out.println(universities.size() == universitiesFromJson.size());
        universities.forEach(university -> {
            String universityJson = JsonUtil.universityToJson(university);
            // проверяем, что json из отдельного элемента создан успешно
            System.out.println(universityJson);
            University universityFromJson = JsonUtil.jsonToUniversity(universityJson);
            // проверяем, что обратно элемент воссоздаётся
            System.out.println(universityFromJson);
        });


        List<Student> students = ReadFromExcel.readFromFileStudent("universityInfo.xls");
        StudentComparator studentComparator = ComparatorUtil.getStudentComparator(StudentComparatorType.AVG_EXAM_SCORE);
        students.sort(studentComparator);

        String studentsJson = JsonUtil.studentListToJson(students);
        // проверяем, что json создан успешно
        System.out.println(studentsJson);
        List<Student> studentsFromJson = JsonUtil.jsonToStudentList(studentsJson);
        // проверяем, что обратно коллекция воссоздаётся в таком же количестве элементов
        System.out.println(students.size() == studentsFromJson.size());
        students.forEach(student -> {
            String studentJson = JsonUtil.studentToJson(student);
            // проверяем, что json из отдельного элемента создан успешно
            System.out.println(studentJson);
            Student studentFromJson = JsonUtil.jsonToStudent(studentJson);
            // проверяем, что обратно элемент воссоздаётся
            System.out.println(studentFromJson);
        });
        List<Statistics> statisticsList = StatisticsUtil.createStatistics(students, universities);
        XlsWriter.writeXlsStatistics(statisticsList, "statistics.xlsx");
    }
}