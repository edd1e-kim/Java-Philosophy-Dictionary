import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class DictionaryManager {
    private HashMap<String, HanjaTerm> dictionary = new HashMap<>();

    // 단어 메모리 등록
    public void addTerm(String hanja, String hangul, String definition, String category) {
        HanjaTerm term = new HanjaTerm(hanja, hangul, definition, category);
        dictionary.put(hanja, term);
    }

    // 단어 검색 (한자 또는 단어 명으로 직접 매핑 검색)
    public HanjaTerm searchTerm(String query) {
        return dictionary.get(query);
    }

    // 단어 삭제
    public boolean deleteTerm(String hanja) {
        if (dictionary.containsKey(hanja)) {
            dictionary.remove(hanja);
            return true;
        }
        return false;
    }

    // 전체 조회를 위한 리스트 반환
    public ArrayList<HanjaTerm> getAllTerms() {
        return new ArrayList<>(dictionary.values());
    }

    // 퀴즈 출제용 셔플 알고리즘 기반 랜덤 단어 반환
    public HanjaTerm getRandomTerm() {
        if (dictionary.isEmpty()) return null;
        ArrayList<HanjaTerm> list = new ArrayList<>(dictionary.values());
        Collections.shuffle(list);
        return list.get(0);
    }
}