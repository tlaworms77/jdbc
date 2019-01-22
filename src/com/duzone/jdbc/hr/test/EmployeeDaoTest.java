package com.duzone.jdbc.hr.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.duzone.jdbc.hr.dao.EmployeeDao;
import com.duzone.jdbc.hr.vo.EmployeeVo;

public class EmployeeDaoTest {

	public static void main(String[] args) {

		serviceOperation();

	}

	public static void serviceOperation() {

		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.println("무엇을 하시겠습니까?");
			System.out.println("(1:사원검색),(2:사원추가),(3:사원전체출력),(4:프로그램종료)");
			System.out.print(">>");
			String key = scanner.nextLine();
			switch (key) {
			case "1":
				searchEmployee(scanner);
				break;
			case "2":
				insertEmployee(scanner);
				break;
			case "3":
				getListEmployee();
				break;
			case "4":
				System.out.println("프로그램을 종료하겠습니다.");
				if(scanner != null) {
					System.out.println("scanner 자원해제합니다.");
					scanner.close();
				}
				System.out.println("정상종료합니다.");
				return ;
			default:
				System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}

	@SuppressWarnings("unused")
	private static void searchEmployee(Scanner sc) {

		System.out.println("찾으실 사원의 이름을 입력해주세요.");
		System.out.print(">>");
		
		String name = sc.nextLine();

		List<EmployeeVo> list = new EmployeeDao().getList(name);

		if (list.isEmpty()) {
			System.out.println("찾으실 사원의 이름은 존재하지 않습니다.");
		}

		for (EmployeeVo vo : list) {
			System.out.println(vo);
		}

	}

	@SuppressWarnings("unused")
	private static void insertEmployee(Scanner sc) {
		
		System.out.println("사원 추가 형식 : 성/이름/이메일/전화번호");
		System.out.print(">>");
		String emp_data = sc.nextLine();
		EmployeeVo employeeVo = new EmployeeVo();
		String tokens[] = emp_data.split("/");

		employeeVo.setFirstName(tokens[0]);
		employeeVo.setLastName(tokens[1]);
		employeeVo.setEmail(tokens[2]);
		employeeVo.setTel(tokens[3]);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String date = sdf.format(d);

		employeeVo.setDate(date);

		boolean result = new EmployeeDao().insert(employeeVo);
		
		if(result) {
			System.out.println("사원 추가 사항이 정상적으로 진행됬습니다.");
		} else {
			System.out.println("사원 추가 사항에 에러가 발생됬습니다.");
		}
		
	}

	public static void getListEmployee() {
		List<EmployeeVo> list = new EmployeeDao().getList();

		for (EmployeeVo vo : list) {
			System.out.println(vo);
		}
	}
}
