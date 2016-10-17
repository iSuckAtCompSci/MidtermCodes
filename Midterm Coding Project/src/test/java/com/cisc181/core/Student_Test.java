package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eMajor;

public class Student_Test {

	static ArrayList<Course> CourseRecords = new ArrayList<Course>();
	static ArrayList<Semester> SemesterRecords = new ArrayList<Semester>();
	static ArrayList<Section> SectionRecords = new ArrayList<Section>();
	static ArrayList<Student> StudentRecords = new ArrayList<Student>();

	@BeforeClass
	public static void setup() throws PersonException {
		Date DOB = new Date(0);

		// three Course records
		Course course1 = new Course("101", 4, eMajor.CHEM);
		Course course2 = new Course("100", 4, eMajor.BUSINESS);
		Course course3 = new Course("300", 4, eMajor.PHYSICS);
		CourseRecords.add(course1);
		CourseRecords.add(course2);
		CourseRecords.add(course3);

		// two Semesters, one for Fall, one for Spring
		Calendar fallStart = Calendar.getInstance();
		Calendar fallEnd = Calendar.getInstance();
		Calendar springStart = Calendar.getInstance();
		Calendar springEnd = Calendar.getInstance();
		fallStart.set(2015, 9, 1);
		fallEnd.set(2015, 12, 20);
		springStart.set(2016, 2, 10);
		springEnd.set(2016, 5, 20);
		Date fallStartDate = fallStart.getTime();
		Date fallEndDate = fallEnd.getTime();
		Date springStartDate = springStart.getTime();
		Date springEndDate = springEnd.getTime();
		Semester semfal = new Semester(fallStartDate, fallEndDate);
		Semester semspr = new Semester(springStartDate, springEndDate);
		SemesterRecords.add(semfal);
		SemesterRecords.add(semspr);

		// two Sections for each Course & Semester
		Section SectionFC1 = new Section(course1.getCourseID(), semfal.getSemesterID());
		Section SectionFC2 = new Section(course1.getCourseID(), semfal.getSemesterID());
		Section SectionFB1 = new Section(course2.getCourseID(), semfal.getSemesterID());
		Section SectionFB2 = new Section(course2.getCourseID(), semfal.getSemesterID());
		Section SectionFP1 = new Section(course3.getCourseID(), semfal.getSemesterID());
		Section SectionFP2 = new Section(course3.getCourseID(), semfal.getSemesterID());
		Section SectionSC1 = new Section(course1.getCourseID(), semspr.getSemesterID());
		Section SectionSC2 = new Section(course1.getCourseID(), semspr.getSemesterID());
		Section SectionSB1 = new Section(course2.getCourseID(), semspr.getSemesterID());
		Section SectionSB2 = new Section(course2.getCourseID(), semspr.getSemesterID());
		Section SectionSP1 = new Section(course3.getCourseID(), semspr.getSemesterID());
		Section SectionSP2 = new Section(course3.getCourseID(), semspr.getSemesterID());
		SectionRecords.add(SectionFC1);
		SectionRecords.add(SectionFC2);
		SectionRecords.add(SectionFB1);
		SectionRecords.add(SectionFB2);
		SectionRecords.add(SectionFP1);
		SectionRecords.add(SectionFP2);
		SectionRecords.add(SectionSC1);
		SectionRecords.add(SectionSC2);
		SectionRecords.add(SectionSB1);
		SectionRecords.add(SectionSB2);
		SectionRecords.add(SectionSP1);
		SectionRecords.add(SectionSP2);

		// ten Student records
		Student a = new Student("A", "Aa", "Ll", DOB, eMajor.CHEM, "Address", "1234567890", "a@udel.edu");
		Student b = new Student("B", "Bb", "Lm", DOB, eMajor.BUSINESS, "Address", "1234567890", "a@udel.edu");
		Student c = new Student("C", "Cc", "Ln", DOB, eMajor.PHYSICS, "Address", "1234567890", "a@udel.edu");
		Student d = new Student("D", "Dd", "Lo", DOB, eMajor.NURSING, "Address", "1234567890", "a@udel.edu");
		Student e = new Student("E", "Ee", "Lp", DOB, eMajor.CHEM, "Address", "1234567890", "a@udel.edu");
		Student f = new Student("F", "Ff", "Lq", DOB, eMajor.NURSING, "Address", "1234567890", "a@udel.edu");
		Student g = new Student("G", "Hh", "Lr", DOB, eMajor.NURSING, "Address", "1234567890", "a@udel.edu");
		Student h = new Student("H", "Ii", "Ls", DOB, eMajor.CHEM, "Address", "1234567890", "a@udel.edu");
		Student i = new Student("I", "Jj", "Lt", DOB, eMajor.PHYSICS, "Address", "1234567890", "a@udel.edu");
		Student j = new Student("J", "Kk", "Lu", DOB, eMajor.BUSINESS, "Address", "1234567890", "a@udel.edu");
		StudentRecords.add(a);
		StudentRecords.add(b);
		StudentRecords.add(c);
		StudentRecords.add(d);
		StudentRecords.add(e);
		StudentRecords.add(f);
		StudentRecords.add(g);
		StudentRecords.add(h);
		StudentRecords.add(i);
		StudentRecords.add(j);
	}

	@Test
	public void test() {
		ArrayList<Enrollment> listEnroll = new ArrayList<Enrollment>();
		for (Student i : StudentRecords) {
			for (Section j : SectionRecords) {
				Enrollment ijEnroll = new Enrollment(i.getStudentID(), j.getSectionID());
				listEnroll.add(ijEnroll);
			}
		}

		for (Enrollment i : listEnroll) {
			i.SetGrade(4.0);
		}
		
		
		// Determine and Test GPA for each student
		for (Student stud : StudentRecords) {
			int count = 0;
			double GPA = 0;
			for (Enrollment enroll : listEnroll) {
				if (count >= 12)
					break;
				else if (enroll.getStudentID().equals(stud.getStudentID())) {
					count++;
					GPA += enroll.getGrade();
				}
			}
			GPA = GPA / 12.0;
			assertEquals(4.0, GPA, 0);
		}

		// Test GPA for each course
		for (Course cou : CourseRecords) {
			int count = 0;
			double GPA = 0;
			for (Section sec : SectionRecords) {
				if (cou.getCourseID().equals(sec.getCourseID())) {
					for (Enrollment enroll : listEnroll) {
						if (count >= 40)
							break;
						else if (enroll.getSectionID().equals(sec.getSectionID())) {
							count++;
							GPA += enroll.getGrade();
						}
					}
				}
			}
			//10 students in each sections, each course has 4 sections
			GPA = GPA / 40.0;
			assertEquals(4.0, GPA, 0);
		}
	}
	
	@Test
	public void test2(){
		//Change major from Chem to Nursing
		assertEquals(StudentRecords.get(0).getMajor(), eMajor.CHEM);
		StudentRecords.get(0).setMajor(eMajor.NURSING);
		assertEquals(StudentRecords.get(0).getMajor(), eMajor.NURSING);
	}
}