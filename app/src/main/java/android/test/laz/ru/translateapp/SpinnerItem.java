package android.test.laz.ru.translateapp;

/**
 * Created by Dmitry Lazarev on 26.03.2017.
 */


//В этом классе хрнятся сокращенные и полные названия языка для отображения в спиннерах
public class SpinnerItem {
    private String displayName;
    private String langShortName;

    public SpinnerItem(String stName, String dispName) {
        displayName = dispName;
        langShortName = stName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLangShortName() {
        return langShortName;
    }

    @Override
    public String toString() {
        return displayName;
    }


}
