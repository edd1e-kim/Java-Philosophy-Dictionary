import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 시스템 기본 인코딩 스트림 스트레스 차단
        Scanner scanner = (System.console() != null) 
    ? new Scanner(System.in, System.console().charset().name()) 
    : new Scanner(System.in);
        DictionaryManager manager = new DictionaryManager();

        // 켜지자마자 파일 시스템 자동 로드
        FileHandler.loadInitialData(manager);

        while (true) {
            System.out.println("\n===== 📖 동양 철학 및 한자 사전 시스템 =====");
            System.out.println("1. 단어 등록  2. 단어 검색  3. 단어 삭제  4. 전체 목록  5. 한자 퀴즈  6. 저장 후 종료");
            System.out.print("▶ 메뉴 선택: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("⚠️ 숫자를 입력해 주세요.");
                scanner.nextLine();
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            if (choice == 1) {
                System.out.print("등록할 한자원어: "); String hanja = scanner.nextLine().trim();
                System.out.print("한글 읽기명: "); String hangul = scanner.nextLine().trim();
                System.out.print("뜻과 철학적 정의: "); String definition = scanner.nextLine().trim();
                System.out.print("학술 범주: "); String category = scanner.nextLine().trim();
                
                manager.addTerm(hanja, hangul, definition, category);
                System.out.println("✏️ 메모리에 등록되었습니다.");

            } else if (choice == 2) {
                System.out.print("🔍 검색할 단어(한자 또는 한글)를 입력하세요: ");
                // 입력값 내 보이지 않는 제어문자 및 공백 완벽 숙청
                String query = scanner.nextLine().trim().replaceAll("[\\p{Z}\\s]", "");
                
                HanjaTerm result = null;
                
                // 전체 데이터를 돌며 한자 형태나 한글 음이 부분 일치하거나 완전 일치하는지 정밀 탐색
                for (HanjaTerm t : manager.getAllTerms()) {
                    String cleanHanja = t.getHanja().replaceAll("[\\p{Z}\\s]", "");
                    String cleanHangul = t.getHangul().replaceAll("[\\p{Z}\\s]", "");
                    
                    if (cleanHanja.equals(query) || cleanHangul.equals(query)) {
                        result = t;
                        break;
                    }
                }
                
                if (result != null) {
                    System.out.println(result);
                } else {
                    System.out.println("❌ 사전에 등록되지 않은 용어입니다.");
                }

            } else if (choice == 3) {
                System.out.print("🗑️ 삭제할 단어 입력: ");
                String target = scanner.nextLine().trim().replaceAll("[\\p{Z}\\s]", "");
                if (manager.deleteTerm(target)) {
                    System.out.println("✅ 삭제 완료되었습니다.");
                } else {
                    System.out.println("❌ 해당 단어를 찾을 수 없습니다.");
                }

            } else if (choice == 4) {
                ArrayList<HanjaTerm> list = manager.getAllTerms();
                if (list.isEmpty()) {
                    System.out.println("📭 사전에 데이터가 로드되지 않았습니다.");
                } else {
                    System.out.println("\n===== 전체 데이터 목록 (" + list.size() + "개 수록) =====");
                    for (HanjaTerm t : list) {
                        System.out.println(t);
                    }
                }

            } else if (choice == 5) {
                HanjaTerm quiz = manager.getRandomTerm();
                if (quiz == null) {
                    System.out.println("⚠️ 출제할 데이터가 없습니다.");
                    continue;
                }
                
                System.out.println("\n[🧩 한자 퀴즈] 다음 정의에 해당하는 용어는 무엇입니까?");
                System.out.println("뜻: " + quiz.getDefinition());
                System.out.print("정답 입력 (한자 또는 한글 음): ");
                String answer = scanner.nextLine().trim().replaceAll("[\\p{Z}\\s]", "");

                String cleanHanja = quiz.getHanja().replaceAll("[\\p{Z}\\s]", "");
                String cleanHangul = quiz.getHangul().replaceAll("[\\p{Z}\\s]", "");

                if (answer.equals(cleanHanja) || answer.equals(cleanHangul)) {
                    System.out.println("🎉 정답입니다!");
                } else {
                    System.out.println("❌ 오답입니다. 정답은 【 " + quiz.getHanja() + " 】 (" + quiz.getHangul() + ") 입니다.");
                }

            } else if (choice == 6) {
                FileHandler.saveData(manager.getAllTerms());
                System.out.println("💾 파일 시스템 동기화 완료.");
                System.out.println("🚪 프로그램을 안전하게 종료합니다.");
                break;
            } else {
                System.out.println("⚠️ 1~6번 사이의 번호를 선택하세요.");
            }
        }
        scanner.close();
    }
}