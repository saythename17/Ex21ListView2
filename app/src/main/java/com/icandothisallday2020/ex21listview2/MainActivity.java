package com.icandothisallday2020.ex21listview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter adapter;//String 일때만 사용가능한 adapter
    //대량의 데이터
    ArrayList<String> datas=new ArrayList<>();
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        et=findViewById(R.id.edit);
        //리스트뷰에서 보여줄 대량의 데이터 추가
//        datas.add(new String("aaa"));
//        datas.add("bbb");//new String 생략
        //리스트뷰의 item 이 없을 때 보여지는 뷰 설정
        TextView tv=findViewById(R.id.text_empty);
        listView.setEmptyView(tv);
        //리스트뷰에 보여줄 뷰들을 만들어주는 adapter 객체 생성
        adapter=new ArrayAdapter(this,R.layout.list_item,datas);
                //context , 설계도면, 데이터
        //set adapter to listView
        listView.setAdapter(adapter);
        //리스트뷰에 아이템 삭제기능 롱클릭 리스너 추가
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //클릭한 뷰의 데이터 삭제
                datas.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }        });
    }
    public void click(View view) {
        //EditText 에 사용자가 입력한 문자열을 ArrayList(datas) 에 추가
        String s=et.getText().toString();
        datas.add(s);
        //adapter 에게 리스트뷰 갱신 요청(data set change)
        adapter.notifyDataSetChanged();
        //리스트뷰의 스크롤위치 지정
        listView.setSelection(datas.size()-1);//마지막 포지션번호
        //EditText 의 글씨 지우기
        et.setText("");
    }
}
