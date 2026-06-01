import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileHandler {
    private static final String FILE_PATH = "data/dictionary.txt";

    public static void loadInitialData(DictionaryManager manager) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("⚠️ 데이터 파일이 존재하지 않습니다: " + FILE_PATH);
            return;
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            int loadedCount = 0;
            while ((line = br.readLine()) != null) {
                // Windows 개행문자 및 앞뒤 눈에 안 보이는 공백 완벽 제거
                line = line.replace("\r", "").replace("\n", "").trim();
                if (line.isEmpty()) continue;
                
                // BOM 유령 문자 강제 제거
                if (line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }
                
                // 정규식 대신 안전하게 문자열 분리
                String[] parts = line.split("\\|"); 
                if (parts.length >= 3) {
                    String hanja = parts[0].trim();
                    String hangul = parts[1].trim();
                    String definition = parts[2].trim();
                    String category = (parts.length >= 4) ? parts[3].trim() : "일반";
                    
                    // 혹시 모를 유령 특수공백 제거
                    hanja = hanja.replaceAll("[\\p{Z}\\s]", "");
                    hangul = hangul.replaceAll("[\\p{Z}\\s]", "");
                    
                    manager.addTerm(hanja, hangul, definition, category);
                    loadedCount++;
                }
            }
            System.out.println("✅ 데이터베이스 로드 완료: 총 " + loadedCount + "개의 철학 용어가 장착되었습니다.");
        } catch (IOException e) {
            System.out.println("⚠️ 데이터 파일 로드 중 오류: " + e.getMessage());
        }
    }

    public static void saveData(ArrayList<HanjaTerm> list) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(FILE_PATH), StandardCharsets.UTF_8))) {
            for (HanjaTerm term : list) {
                bw.write(term.getHanja() + "|" + term.getHangul() + "|" + 
                         term.getDefinition() + "|" + term.getCategory());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ 데이터 저장 중 오류: " + e.getMessage());
        }
    }
}