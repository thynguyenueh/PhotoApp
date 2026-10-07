package thanhdnh.ueh.edu.article_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    private Switch swEnableFilter, swDarkMode, swNotifications;
    private RadioGroup rgGender;
    private RadioButton rbAll, rbMale, rbFemale;
    private EditText etMinAge;
    private SharedPreferencesManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        prefManager = new SharedPreferencesManager(this);

        swEnableFilter = findViewById(R.id.swEnableFilter);
        swDarkMode = findViewById(R.id.swDarkMode);
        swNotifications = findViewById(R.id.swNotifications);

        rgGender = findViewById(R.id.rgGender);
        rbAll = findViewById(R.id.rbAll);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);

        etMinAge = findViewById(R.id.etMinAge);
        Button btnSave = findViewById(R.id.btnSaveSettings);

        loadCurrentSettings();

        btnSave.setOnClickListener(v -> saveSettings());
    }

    private void loadCurrentSettings() {
        swEnableFilter.setChecked(prefManager.isEnableFilter());
        swDarkMode.setChecked(prefManager.isDarkMode());
        swNotifications.setChecked(prefManager.isNotifications());

        String savedGender = prefManager.getGenderFilter();
        if ("Nam".equalsIgnoreCase(savedGender)) {
            rbMale.setChecked(true);
        } else if ("Nữ".equalsIgnoreCase(savedGender)) {
            rbFemale.setChecked(true);
        } else {
            rbAll.setChecked(true);
        }

        int savedAge = prefManager.getAgeFilter();
        etMinAge.setText(savedAge > 0 ? String.valueOf(savedAge) : "");
    }

    private void saveSettings() {
        prefManager.setEnableFilter(swEnableFilter.isChecked());
        prefManager.setDarkMode(swDarkMode.isChecked());
        prefManager.setNotifications(swNotifications.isChecked());

        // Lấy giới tính từ RadioButton
        int selectedId = rgGender.getCheckedRadioButtonId();
        if (selectedId == R.id.rbMale) {
            prefManager.setGenderFilter("Nam");
        } else if (selectedId == R.id.rbFemale) {
            prefManager.setGenderFilter("Nữ");
        } else {
            prefManager.setGenderFilter("Tất cả");
        }

        // Lấy tuổi
        String ageStr = etMinAge.getText().toString().trim();
        int age = ageStr.isEmpty() ? 0 : Integer.parseInt(ageStr);
        prefManager.setAgeFilter(age);

        Toast.makeText(this, "Đã lưu cài đặt!", Toast.LENGTH_SHORT).show();
        finish(); // Quay về MainActivity
    }
}