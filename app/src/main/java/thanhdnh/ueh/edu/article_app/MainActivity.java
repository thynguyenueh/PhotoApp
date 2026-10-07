package thanhdnh.ueh.edu.article_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recyclerViewUsers);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    List<User> userList = initData();

    UserAdapter adapter = new UserAdapter(this, userList);
    recyclerView.setAdapter(adapter);
  }

  private List<User> initData() {
    List<User> userList = new ArrayList<>();

    userList.add(new User(
            1,
            "nguyen_bao_thy",
            "pass123",
            "https://i.pravatar.cc/300?img=5",
            "Business Analyst Intern | ERP & Database System Designer."
    ));

    userList.add(new User(
            2,
            "tuan_linh_dev",
            "pass456",
            "https://i.pravatar.cc/300?img=12",
            "Android Developer passionate about mobile technologies."
    ));

    userList.add(new User(
            3,
            "ueh_student",
            "pass789",
            "https://i.pravatar.cc/300?img=3",
            "UEH University Student | E-Commerce & Tech Researcher."
    ));

    return userList;
  }
}