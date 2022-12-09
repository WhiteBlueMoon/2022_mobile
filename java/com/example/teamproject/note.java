package com.example.teamproject;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class note extends Fragment {

    private View view,note_dialog;
    private ListView Note_List;

    private ArrayList<ListItem> List = new ArrayList<ListItem>();
    private MyAdapter myAdapter;

    private Button note_add;
    private EditText dialog_name;

    //notelist.dat 에 노트 이름들 적어 넣을꺼임
    private String file = "notelist.dat";
    private File note;

    private String textline,noteline;

    private ArrayList<String> NoteText = new ArrayList<String>();
    private ArrayList<String> fileText = new ArrayList<String>();

    private boolean error = false;

    private int index=0;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(R.layout.score_bd, container, false);
        view = inflater.inflate(R.layout.note_bd, container, false);

        //listview 관련
        Note_List = (ListView) view.findViewById(R.id.Note_List);
        myAdapter = new MyAdapter(view.getContext(),List);

        //listview 와 어댑터 연결
        Note_List.setAdapter(myAdapter);

        //listview 노트 추가용도

        note_add = (Button) view.findViewById(R.id.note_add);

        //파일 읽어오기
        try {
            FileInputStream fileInputStream = getActivity().openFileInput(file);

            byte[] bytes = new byte[8192];
            fileInputStream.read(bytes);
            String str= (new String(bytes).trim());

            List<String> ByteData = new ArrayList<String>();
            ByteData = Arrays.asList(str.split(";;;"));
            System.out.println("ByteData1 :" + ByteData);

            FileInputStream fileInputStream1;

            str = null;
            Arrays.fill(bytes,(byte) 0);


            for (int n = 0; n < ByteData.size();n++){
                //파일 내부에 있는 값도 탐색해서 추가
                try {

                    System.out.println(ByteData.get(n) + ": 배열값 가져와봐...");
                    String ByteDataFile = ByteData.get(n)+".dat";
                    System.out.println("ByteDataFile : "+ByteDataFile);

                    fileInputStream1 = getActivity().openFileInput(ByteData.get(n).toString()+".dat");
                    byte[] b = new byte[8192];
                    fileInputStream1.read(bytes);

                    str = (new String(bytes).trim());
                    System.out.println("불러온 내용이 있냐...? : "+str);

                    //리스트 추가
                    NoteText.add(str);

                    //바이트 값이 비었는가 아닌가
                    if(Arrays.equals(bytes,new byte[8192])){
                        List.add(new ListItem(ByteData.get(n),""));
                    }else {
                        //메모해둔 값이 10보다 작다면 그냥 그대로 표현, 크다면 10문자 이하로 표현
                        if (str.length() < 10){
                            List.add(new ListItem(ByteData.get(n),str));

                        }else {
                            List.add(new ListItem(ByteData.get(n),str.substring(0,10)));
                        }

                    }

                    //파일 닫고 값들 초기화
                    fileInputStream1.close();
                    str=null;
                    Arrays.fill(bytes,(byte) 0);

                }catch (FileNotFoundException e){
                    System.out.println("파일 없었음");
                    break;
                }

            }

            fileInputStream.close();

        } catch (FileNotFoundException e) {
            //파일을 읽지 못함
            error = true;
            System.out.println("파일 읽기 실패 1");
        } catch (IOException e) {
            error = true;
        }



        //다음에 사용할수 있게 초기화
        textline = null;

        //리스트뷰 갱신
        myAdapter.notifyDataSetChanged();


        //dialog로 노트 생성
        //현재 오류가 다수인 공간.
        note_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note_dialog = (View) View.inflate(getActivity(),R.layout.note_dialog,null);

                dialog_name = (EditText) note_dialog.findViewById(R.id.dialog_name);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("노트 이름");
                builder.setView(note_dialog);

                //생성한다고 하였을때
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String str = dialog_name.getText().toString();
                        boolean same = false;


                        //동일한 이름이 있는지 없는지 판단 없어야 만듬
                        for (int n = 0 ; n < List.size();n++){
                            if(str.equals(List.get(n).getItemName()) == true ) {
                                same = true;
                            }
                        }
                        //동일한게 없었을떄
                        if (same == false){

                        String str2 = str+";;;";  //한줄 띄어쓰기용도로 추가해넣음

                        //error 의 내용은 파일이 없다면 true 를 주기에
                        if(error == true){
                            try {
                                //list.dat 를 만들고 거기에 맞는 파일 이름넣고 그파일 이름의 파일 생성
                                FileOutputStream fileOutputStream = getActivity().openFileOutput(file,getActivity().MODE_PRIVATE);
                                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                                bufferedOutputStream.write(str2.getBytes());
                                bufferedOutputStream.close();
                                fileOutputStream.close();


                                //list.dat 에 이름이 들어간 빈 파일 생성
                                FileOutputStream fileOutputStream1 = getActivity().openFileOutput(str+".dat",getActivity().MODE_PRIVATE);
                                fileOutputStream1.write("".getBytes());
                                fileOutputStream1.close();

                                //제목과 빈 공란 생성
                                List.add(new ListItem(str,""));
                                NoteText.add(null);

                                //파일을 만들었기에 false 로 만듬
                                error=false;
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.out.println("오류발생");
                            }
                        }else if(error == false) {
                            //오류 처리 있어서 여기서 오류 가능성은 낮음
                            //이는 만일 기존 파일이 있을때 저장하는방식

                            try {

                                FileInputStream fileInputStream = getActivity().openFileInput(file);

                                byte[] bytes = new byte[8192];
                                fileInputStream.read(bytes);
                                String string = (new String(bytes).trim());

                                List<String> ByteData = new ArrayList<String>();
                                ByteData = Arrays.asList(string.split(";;;"));
                                //System.out.println("ByteData2 탐색 : "+ByteData);
                                fileInputStream.close();

                                //리스트에서 뽑아와서 하나 더 추가 용도
                                FileOutputStream fops = getActivity().openFileOutput(file,getActivity().MODE_PRIVATE);

                                //list.dat 에 추가함
                                string = string + str2;
                                fops.write(string.getBytes());
                                fops.close();

                                //빈 파일 생성
                                FileOutputStream fileOutputStream1 = getActivity().openFileOutput(str+".dat",getActivity().MODE_PRIVATE);
                                fileOutputStream1.write("".getBytes());
                                fileOutputStream1.close();

                                List.add(new ListItem(str,""));
                                NoteText.add(null);

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                                System.out.println("파일 읽어오기 실패");
                            } catch (IOException e) {
                                System.out.println("파일 저장 실패 ?");
                            }


                            //이 아래 코드는 만일 데이터가 이어서 저장이 안되었을경우 주석을 해제
                            //파일내용 모두 다시 일일히 담아뒀다가 저장하는 역할
                            //코드 내용이 잘못되어서 만약 한다고하면 다시 작성해야함

                            /*
                            try {
                                bufferedReader2 = new BufferedReader(new FileReader(file));
                                while ((textline = bufferedReader2.readLine()) != null){
                                    fileText.add(textline);
                                }
                                bufferedReader2.close();
                            } catch (FileNotFoundException e) {
                                System.out.println("파일 읽기 실패");
                                try {
                                    bufferedReader2.close();
                                } catch (IOException ex) {
                                    System.out.println("close 실패");
                                }
                            } catch (IOException e) {
                                System.out.println("파일 내용물 읽기 실패");
                                try {
                                    bufferedReader2.close();
                                } catch (IOException ex) {
                                    System.out.println("close 실패");
                                }
                            }
                            try {
                                bufferedWriter1 = (new BufferedWriter(new FileWriter(file)));
                                for (int n = 0 ; n < fileText.size() ; n++){
                                    bufferedWriter1.write(fileText.get(n));
                                    bufferedWriter1.newLine();
                                }
                                bufferedWriter1.write(str);
                                bufferedWriter1.newLine();
                                bufferedWriter1.close();
                                List.add(new ListItem(str,""));
                            } catch (IOException e) {
                                System.out.println("파일 읽기 실패");
                            }
                            */
                        }
                        }
                        dialogInterface.dismiss();

                    }
                });

                //생성하지 않았다고 하였을때, 만들기는했으나 원래 기능따윈 안넣는다.
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                //다이얼로그 보이기
                builder.show();
            }
        });

        //리스트뷰 아이템 클릭시 노트 띄우게 하는거
        Note_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),NoteWrite.class);
                intent.putExtra("title", new String(List.get(i).getItemName()));
                if (NoteText.get(i) != null){
                    intent.putExtra("text",new String(NoteText.get(i)));
                }
                index = i;

                startActivityForResult(intent,13);
            }
        });

        Note_List.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                return false;
            }
        });
        registerForContextMenu(Note_List);
        return view;
    }

    //context 메뉴를 사용해서 리스트뷰 제거만들기
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        if (v == Note_List)
            menuInflater.inflate(R.menu.tem_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete){

            //List.dat 에서 지우기
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = getActivity().openFileInput(file);
                byte[] bytes = new byte[8192];
                fileInputStream.read(bytes);
                fileInputStream.close();
                String str= (new String(bytes).trim());
                System.out.println("str replace 이전"+str);
                //List 에서 가져온 파일 명 지우기
                str = str.replace(List.get(index).getItemName()+";;;","");
                System.out.println("str replace 이후"+str);

                FileOutputStream fileOutputStream = getActivity().openFileOutput(file,getActivity().MODE_PRIVATE);
                //지운거 그대로 List.dat 에 업데이트
                fileOutputStream.write(str.getBytes());
                fileOutputStream.close();

                //이름 지우기가 완료되면 파일도 지움
                getActivity().deleteFile(List.get(index).getItemName()+".dat");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //리스트뷰에서 이름도 완전 빠짐
            List.remove(index);
            NoteText.remove(index);
            myAdapter.notifyDataSetChanged();
            return true;
        }
        return true;
    }

    //노트를 띄운 화면에서 result 값이 돌아왔을때(저장을 요구 했을때)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 13 && resultCode == 16) {

            //메모 내용 저장
            try {
                /*

                FileInputStream fileInputStream = getActivity().openFileInput(List.get(index).getItemName()+".dat");
                byte[] bytes = new byte[8192];
                fileInputStream.read(bytes);
                fileInputStream.close();
                String str= (new String(bytes).trim());
                System.out.println("notewrite test 1 : "+ str);


                 */

                FileOutputStream fileOutputStream = getActivity().openFileOutput(List.get(index).getItemName()+".dat",getActivity().MODE_PRIVATE);
                fileOutputStream.write(data.getStringExtra("text").getBytes());
                fileOutputStream.close();

                String st = List.get(index).getItemName();
                String text = data.getStringExtra("text");

                NoteText.set(index,text);
                List.remove(index);

                //글자수 제한
                if (text.length() < 10){
                    List.add(index,new ListItem(st, NoteText.get(index)));
                }else {
                    List.add(index,new ListItem(st, NoteText.get(index).substring(0,10)));
                }

                //화면 갱신
                myAdapter.notifyDataSetChanged();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("메모 저장시 저장 파일 찾지못함");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("메모 저장시 메모를 저장하지 못함");
            }

        }
    }
}
