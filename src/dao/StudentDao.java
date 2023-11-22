package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

import dto.StudentDto;

// Data Access Object : 데이터를 취급하는 클래스
public class StudentDao {
	Scanner sc = new Scanner(System.in);

	// 학생 데이터 관리 배열
	private StudentDto student[];
	
	private int count;
	
	// 추가, 삭제, 검색, 수정 (CRUD)
	public StudentDao() {
		count = 0;
		
		student = new StudentDto[10];	// 변수만 생성
		// StudentDto student1, student2, student3, ...;
				
		// dto를 생성
//		for (int i = 0; i < student.length; i++) {
//			student[i] = new StudentDto();
//		}
	}	

	public void insert() {
		System.out.println("학생 정보 입력입니다");	

		System.out.print("이름 >> ");
		String name = sc.next();

		System.out.print("나이 >> ");
		int age = sc.nextInt();

		System.out.print("신장 >> ");
		double height = sc.nextDouble();

		System.out.print("주소 >> ");
		String address = sc.next();

		System.out.print("국어 >> ");
		int kor = sc.nextInt();

		System.out.print("영어 >> ");
		int eng = sc.nextInt();

		System.out.print("수학 >> ");
		int math = sc.nextInt();

		student[count] = new StudentDto(name, age, height, address, kor, eng, math);		
		count++;	// 배열의 다음으로 이동
	}
	
	public void delete() {
		// 삭제할 학생 이름 입력	
		System.out.println(" 삭제할 학생 이름을 입력하세요. ");		
		System.out.print(" 이름 >> ");
		String name = sc.next();
		
		int index = search(name);
		
		if(index == -1) {
			System.out.println(" 학생정보를 찾을 수 없습니다.");
			return;
		}
		
		student[index].setName("");
		student[index].setAge(0);
		student[index].setHeight(0.0);
		student[index].setAddress("");
		student[index].setKor(0);
		student[index].setEng(0);
		student[index].setMath(0);
		
		System.out.println(" 학생정보를 삭제하였습니다.");
	}
	
	public void select() {
		System.out.println(" 검색할 학생 이름을 입력하세요. ");		
		System.out.print(" 이름 >> ");
		String name = sc.next();
		
		int index = search(name);
		
		if(index == -1) {
			System.out.println(" 학생정보를 찾을 수 없습니다.");
			return;
		}
		
		System.out.print(student[index].getName()+" ");
		System.out.print(student[index].getAge()+" ");
		System.out.print(student[index].getHeight()+" ");
		System.out.print(student[index].getAddress()+" ");
		System.out.print(student[index].getKor()+" ");
		System.out.print(student[index].getEng()+" ");
		System.out.print(student[index].getMath()+" ");
		
		System.out.println();
	}
	public void update() {
		System.out.println(" 수정할 학생 이름을 입력하세요. ");		
		System.out.print(" 이름 >> ");
		String name = sc.next();
		
		int index = search(name);
		
		if(index == -1) {
			System.out.println(" 학생정보를 찾을 수 없습니다.");
			return;
		}
		
		System.out.print(" 국어 점수 >> ");
		int lang = sc.nextInt();

		System.out.print(" 영어 점수 >> ");
		int eng = sc.nextInt();

		System.out.print(" 수학 점수 >> ");
		int math = sc.nextInt();
		
		student[index].setKor(lang);
		student[index].setEng(eng);
		student[index].setMath(math);
		System.out.println(" 학생정보가 수정되었습니다.");

	}
	
	public int search(String name) {
		int index = -1;
		for (int i = 0; i < student.length; i++) {
			StudentDto dto = student[i];
			if(dto != null) {
				if(name.equals(dto.getName())) {	// 중요!!!
					index = i;
					break;
				}
			}
		}
		return index;
	}
	
	public void saveFile() {
		// 파일 생성
		File file = new File("C:\\tmp\\student.txt");  // newfile에 대한 pointer 생성

		try {
			if(file.createNewFile()) {
				System.out.println(" 파일이 생성되었습니다.");
			}
			else {
			}
		}catch(IOException e) {
			e.printStackTrace();
		}


		// 파일 쓰기
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

			for(int i = 0; i < student.length; i++) {
				if(student[i] != null) {
					pw.print(student[i].getName() + " ");
					pw.print(student[i].getAge() + " ");
					pw.print(student[i].getHeight() + " ");
					pw.print(student[i].getAddress() + " ");
					pw.print(student[i].getKor() + " ");
					pw.print(student[i].getEng() + " ");
					pw.print(student[i].getMath() + " ");
				}
				pw.println();
			}
			pw.close();
			System.out.printf(" 파일 저장 완료.\n");

		}catch(IOException e) {
			e.printStackTrace();			
		}
	}

	public void loadFile() {
		// 불러올 파일 이름 입력
		Scanner sc = new Scanner(System.in);		
		System.out.print(" 불러올 파일명을 입력하시오 >> ");
		String title = sc.nextLine();
		count = 0;

		File file = new File("C:\\tmp\\" + title + ".txt");

		try {

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String str = "";
			while((str = br.readLine()) != null){
				String array[] = str.split(" ");
				student[count].setName(array[0]);
				student[count].setName(array[1]);
				student[count].setName(array[2]);
				student[count].setName(array[3]);
				student[count].setName(array[4]);
				student[count].setName(array[5]);
				student[count].setName(array[6]);
				count ++;
			}
			br.close();
		} catch(FileNotFoundException e) {
			System.out.print(" " + title + ".txt 파일을 찾을 수 없습니다.\n");
			return;			
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(" " + title + ".txt 파일 불러오기 완료.");
	}
	
	public void allData() {
		for (int i = 0; i < student.length; i++) {
			StudentDto dto = student[i];
			if(dto != null) {
				System.out.println(dto.toString());
			}
		}
	}
	
	
}





