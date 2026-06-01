import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class DictionaryManager {
    // 빠른 검색을 위해 한자어(Key)와 HanjaTerm 객체(Value)를 매핑하는 HashMap 사용
    private HashMap<String, HanjaTerm> dictionary = new HashMap<>();

    // 1. 단어 추가/등록
    public void addTerm(String hanja, String hangul, String definition, String category) {
        HanjaTerm term = new HanjaTerm(hanja, hangul, definition, category);
        dictionary.put(hanja, term);
    }

    // 2. 단어 검색
    public HanjaTerm searchTerm(String hanja) {
        return dictionary.get(hanja); // 없으면 null 반환
    }

    // 3. 단어 삭제
    public boolean deleteTerm(String hanja) {
        if (dictionary.containsKey(hanja)) {
            dictionary.remove(hanja);
            return true;
        }
        return false;
    }

    // 4. 전체 단어 목록 반환 (순회 출력용)
    public ArrayList<HanjaTerm> getAllTerms() {
        return new ArrayList<>(dictionary.values());
    }

    // 5. 퀴즈를 위한 무작위 단어 1개 추출
    public HanjaTerm getRandomTerm() {
        if (dictionary.isEmpty()) return null;
        
        // HashMap의 값들을 임시 ArrayList로 변환 후 무작위로 섞음
        ArrayList<HanjaTerm> list = new ArrayList<>(dictionary.values());
        Collections.shuffle(list);
        return list.get(0); // 첫 번째 단어 반환
    }
}