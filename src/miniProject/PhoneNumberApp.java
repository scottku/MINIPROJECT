package miniProject;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PhoneNumberApp {

	public static void main(String[] args) {
		System.out.println("*************************************************");
		System.out.println("*		전화번호 관리 프로그램			*");
		System.out.println("*************************************************");
		
		
		while(true) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("");
			System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
			System.out.println("-------------------------------------------------");
			System.out.print(">메뉴 번호: ");
			int num = scanner.nextInt();
			if(num == 1) { // 리스트 불러오기
				
				
				PhoneNumberDAO dao = new PhoneNumberDAOImpl();
				List<PhoneNumberVO> list = dao.getList();
				
				Iterator<PhoneNumberVO> it = list.iterator();
				
				System.out.println("1. 리스트: ");
				
				while(it.hasNext()) {
					PhoneNumberVO item = it.next();
					System.out.printf("%d\t%s\t%s\t%s%n",
							item.getChar_id(),
							item.getChar_name(),
							item.getChar_hp(),
							item.getChar_tel());
				}
				
			}
			else if (num == 2) { // 신규 전화번호 등록
				System.out.print("이름을 입력하세요: ");
				String name = scanner.next();
				
				
				
				System.out.print("휴대폰 번호를 입력하세요: ");
				String hp = scanner.next();
				
				
				System.out.print("집 전화번호를 입력하세요: ");
				String tel = scanner.next();
				
				PhoneNumberVO vo = new PhoneNumberVO(null, name, hp, tel);
				PhoneNumberDAO dao = new PhoneNumberDAOImpl();
				
				boolean success = dao.insert(vo);
				
				System.out.println("Insert " + (success? "성공":"실패"));
				
			}
			else if (num == 3) { // 전화번호 삭제
				
				System.out.print("삭제할 번호: ");
				int num1 = scanner.nextInt();
				
				PhoneNumberDAO dao = new PhoneNumberDAOImpl();
				boolean success = dao.delete(Long.valueOf(num1));
				
				System.out.println("Delete " + (success ? "성공":"실패"));
				
				
				continue;
				
			}
			else if (num == 4) { // 검색
				
				System.out.print("검색어: ");
				String char1 = scanner.next();
				
				PhoneNumberDAO dao = new PhoneNumberDAOImpl();
				List<PhoneNumberVO> list = dao.search(char1);
				
				Iterator<PhoneNumberVO> it = list.iterator();
				
				while(it.hasNext()) {
					PhoneNumberVO vo = it.next();
					System.out.printf("%d\t%s\t%s\t%s%n", 
							vo.getChar_id(),
							vo.getChar_name(),
							vo.getChar_hp(),
							vo.getChar_tel());
				}
				
				continue;
			}
			else if (num == 5) { // 종료
				System.out.println("*************************************************");
				System.out.println("*		이용해 주셔서 감사합니다.		*");
				System.out.println("*************************************************");
				
				scanner.close();
				break;
			}
			else {
				System.out.print("다시 입력해 주세요");
				
				continue;
			}
			
		}
		
	}
}
