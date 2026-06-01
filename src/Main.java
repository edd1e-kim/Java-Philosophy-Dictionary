import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DictionaryManager manager = new DictionaryManager();
        Scanner scanner = new Scanner(System.in);

        // 테스트용 기본 데이터 3개 선점 (매번 치기 귀찮으므로 미리 세팅)
        manager.addTerm("陰陽", "음양", "우주 만물을 구성하는 상반된 두 가지 성질의 기운", "우주론");
        manager.addTerm("相互對待", "상호대대", "서로 반대되면서도 의존하며 상호작용하는 관계", "인식론");
        manager.addTerm("水火不交", "수화불교", "물과 불의 기운이 서로 섞이지 못하고 어긋나는 상태", "한의학");

        while (true) {
            System.out.println("\n===== 📖 동양 철학 한자 사전 시스템 =====");
            System.out.println("1. 단어 등록  2. 단어 검색  3. 단어 삭제  4. 전체 목록  5. 한자 퀴즈  6. 프로그램 종료");
            System.out.print("▶ 메뉴 선택: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // 숫자 입력 후 남은 줄바꿈 문자(엔터) 제거

            if (choice == 1) {
                System.out.print("등록할 한자(예: 五行): "); String hanja = scanner.nextLine();
                System.out.print("한글 읽기(예: 오행): "); String hangul = scanner.nextLine();
                System.out.print("뜻과 철학적 정의: "); String definition = scanner.nextLine();
                System.out.print("학술 범주: "); String category = scanner.nextLine();
                
                manager.addTerm(hanja, hangul, definition, category);
                System.out.println("✏️ 단어가 성공적으로 등록되었습니다.");

            } else if (choice == 2) {
                System.out.print("🔍 검색할 한자를 입력하세요: ");
                String query = scanner.nextLine();
                HanjaTerm result = manager.searchTerm(query);
                
                if (result != null) {
                    System.out.println(result);
                } else {
                    System.out.println("❌ 사전에 등록되지 않은 용어입니다.");
                }

            } else if (choice == 3) {
                System.out.print("🗑️ 삭제할 한자를 입력하세요: ");
                String target = scanner.nextLine();
                
                if (manager.deleteTerm(target)) {
                    System.out.println("✅ 성공적으로 삭제되었습니다.");
                } else {
                    System.out.println("❌ 해당 단어를 찾을 수 없습니다.");
                }

            } else if (choice == 4) {
                ArrayList<HanjaTerm> list = manager.getAllTerms();
                if (list.isEmpty()) {
                    System.out.println("📭 사전에 등록된 단어가 없습니다.");
                } else {
                    System.out.println("\n===== 전체 등록 단어 목록 (" + list.size() + "개) =====");
                    for (HanjaTerm t : list) {
                        System.out.println(t);
                    }
                }

            } else if (choice == 5) {
                HanjaTerm quiz = manager.getRandomTerm();
                if (quiz == null) {
                    System.out.println("⚠️ 퀴즈를 출제할 단어가 부족합니다. 단어를 먼저 등록하세요.");
                    continue;
                }
                
                System.out.println("\n[🧩 한자 퀴즈] 다음 철학적 정의에 해당하는 '한자어'를 맞히세요.");
                System.out.println("뜻: " + quiz.getDefinition());
                System.out.print("정답 입력(한자): ");
                String answer = scanner.nextLine();

                if (answer.trim().equals(quiz.getHanja())) {
                    System.out.println("🎉 정답입니다! 훌륭합니다.");
                } else {
                    System.out.println("❌ 오답입니다. 정답은 【 " + quiz.getHanja() + " 】 (" + quiz.getHangul() + ") 입니다.");
                }

            } else if (choice == 6) {
                System.out.println("🚪 프로그램을 종료합니다. 이용해 주셔서 감사합니다.");
                break;
            } else {
                System.out.println("⚠️ 잘못된 입력입니다. 1~6번 사이의 번호를 선택하세요.");
            }
        }
        scanner.close();
    }
}