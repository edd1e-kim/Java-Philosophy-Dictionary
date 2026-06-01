public class HanjaTerm {
    private String hanja;       // 한자 (예: 陰陽)
    private String hangul;      // 한글 읽기 (예: 음양)
    private String definition;  // 철학적 정의 및 설명
    private String category;    // 범주 (예: 우주론, 인식론)

    // 생성자: 객체를 만들 때 데이터를 한 번에 주입합니다.
    public HanjaTerm(String hanja, String hangul, String definition, String category) {
        this.hanja = hanja;
        this.hangul = hangul;
        this.definition = definition;
        this.category = category;
    }

    // 데이터를 가져오고 수정하기 위한 Getter / Setter
    public String getHanja() { return hanja; }
    public void setHanja(String hanja) { this.hanja = hanja; }

    public String getHangul() { return hangul; }
    public void setHangul(String hangul) { this.hangul = hangul; }

    public String getDefinition() { return definition; }
    public void setDefinition(String definition) { this.definition = definition; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    // 콘솔 창에서 System.out.println(term)을 했을 때 예쁘게 보여주기 위해 오버라이딩합니다.
    @Override
    public String toString() {
        return "----------------------------------------\n" +
               "【 " + hanja + " 】 (" + hangul + ") - 범주: " + category + "\n" +
               "뜻/설명: " + definition + "\n" +
               "----------------------------------------";
    }
}