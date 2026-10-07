package thanhdnh.ueh.edu.article_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

  private DatabaseHelper dbHelper;
  private SharedPreferencesManager prefManager;
  private UserAdapter adapter;
  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    dbHelper = new DatabaseHelper(this);
    prefManager = new SharedPreferencesManager(this);

    // Tạo dữ liệu mẫu đa dạng Nam/Nữ/Tuổi
    if (dbHelper.getUsersCount() == 0) {
      initSampleData();
    }

    recyclerView = findViewById(R.id.recyclerViewUsers);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    Button btnOpenSettings = findViewById(R.id.btnOpenSettings);
    if (btnOpenSettings != null) {
      btnOpenSettings.setOnClickListener(v -> {
        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
        startActivity(intent);
      });
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    loadDataWithFilter(); // Cập nhật lại danh sách mỗi khi từ Setting trở về
  }

  private void initSampleData() {
    dbHelper.addUser(new User(1, "nguyen_bao_thy", "123", "https://i.pravatar.cc/300?img=5", "BA Intern | UEH Student", "Nữ", 22));
    dbHelper.addUser(new User(2, "tuan_linh_dev", "456", "https://i.pravatar.cc/300?img=12", "Android Developer", "Nam", 24));
    dbHelper.addUser(new User(3, "minh_thu", "789", "https://i.pravatar.cc/300?img=9", "UI/UX Designer", "Nữ", 19));
    dbHelper.addUser(new User(4, "hoang_nam", "101", "https://i.pravatar.cc/300?img=11", "Data Analyst", "Nam", 26));
  }

  private void loadDataWithFilter() {
    List<User> userList;

    if (prefManager.isEnableFilter()) {
      String gender = prefManager.getGenderFilter();
      int minAge = prefManager.getAgeFilter();
      userList = dbHelper.getUsersFiltered(gender, minAge);
      Toast.makeText(this, "Đang lọc: " + gender + " | Tuổi >= " + minAge, Toast.LENGTH_SHORT).show();
    } else {
      userList = dbHelper.getAllUsers();
    }

    adapter = new UserAdapter(this, userList);
    recyclerView.setAdapter(adapter);
  }
}