public class HanjaTerm {
    private String hanja;       // 한자 또는 용어
    private String hangul;      // 한글 읽기
    private String definition;  // 철학적 정의
    private String category;    // 학술 범주

    public HanjaTerm(String hanja, String hangul, String definition, String category) {
        this.hanja = hanja;
        this.hangul = hangul;
        this.definition = definition;
        this.category = category;
    }

    public String getHanja() { return hanja; }
    public String getHangul() { return hangul; }
    public String getDefinition() { return definition; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return "----------------------------------------\n" +
               "【 " + hanja + " 】 (" + hangul + ") - 범주: " + category + "\n" +
               "뜻/설명: " + definition + "\n" +
               "----------------------------------------";
    }
}