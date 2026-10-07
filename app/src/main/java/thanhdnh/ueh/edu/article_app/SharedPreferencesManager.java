package thanhdnh.ueh.edu.article_app;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String PREF_NAME = "UserSettingsPref";
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // 1. Giới tính (String: "Nam", "Nữ", "Tất cả")
    public void setGenderFilter(String gender) {
        sharedPreferences.edit().putString("KEY_GENDER_FILTER", gender).apply();
    }
    public String getGenderFilter() {
        return sharedPreferences.getString("KEY_GENDER_FILTER", "Tất cả");
    }

    // 2. Tuổi lọc (int)
    public void setAgeFilter(int age) {
        sharedPreferences.edit().putInt("KEY_AGE_FILTER", age).apply();
    }
    public int getAgeFilter() {
        return sharedPreferences.getInt("KEY_AGE_FILTER", 0);
    }

    // 3. Bật/Tắt chế độ Lọc danh sách (boolean)
    public void setEnableFilter(boolean isEnable) {
        sharedPreferences.edit().putBoolean("KEY_ENABLE_FILTER", isEnable).apply();
    }
    public boolean isEnableFilter() {
        return sharedPreferences.getBoolean("KEY_ENABLE_FILTER", false);
    }

    // 4. Dark Mode (boolean)
    public void setDarkMode(boolean isEnable) {
        sharedPreferences.edit().putBoolean("KEY_DARK_MODE", isEnable).apply();
    }
    public boolean isDarkMode() {
        return sharedPreferences.getBoolean("KEY_DARK_MODE", false);
    }

    // 5. Notifications (boolean)
    public void setNotifications(boolean isEnable) {
        sharedPreferences.edit().putBoolean("KEY_NOTIF", isEnable).apply();
    }
    public boolean isNotifications() {
        return sharedPreferences.getBoolean("KEY_NOTIF", true);
    }
}