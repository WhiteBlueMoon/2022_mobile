using System.Collections;
using System.Collections.Generic;
using UnityEditor.UIElements;
using UnityEngine;
using UnityEngine.UI;
using Random = UnityEngine.Random;

public class Find_Rules : MonoBehaviour
{
    public Image Image;
    public Text text;
    public Text[] ButtonText;
    public GameObject NextQ;
    public GameObject[] PrevQ;

    private int[] Result = new int[4]; // 1~4의 값이 중복없이 저장됨
    private int[] ButtonInt = new int[4];
    private int[] MulResult = new int[] { 20, 6, 12, 24, 7, 14, 35, 24 }; // 답이 저장된 배열
    private int ResultCount = 0;
    private int ColorNum;
    private int TextNum;
    private int AnswerNum;
    private int correct_answer; // 이번 문제의 정답
    private int User_answer; // 사용자가 입력한 정답

    // Start is called before the first frame update
    void Start()
    {
        ColorNum = Random.Range(1, 5);
        ColorSet(ColorNum);

        TextNum = Random.Range(2, 5);
        TextSet(TextNum);
    }

    private void ColorSet(int num)
    {
        switch (num)
        {
            case 1:
                Image.color = Color.blue;
                break;
            case 2:
                Image.color = Color.red;
                break;
            case 3:
                Image.color = Color.green;
                break;
            case 4:
                Image.color = Color.yellow;
                break;
                default:
                Debug.Log("랜덤 오류입니다.");
                break;
        }
    }

    private void TextSet(int num)
    {
        text.text = num.ToString();
    }

    public void Note(int num)
    {
        Result[ResultCount] = num; // 버튼 기믹에서 넘긴 색상 순서를 저장
        ResultCount++;
    }

    public void Pass()
    {
        // 넘어온 버튼 색과 같은 색을 int로 찾음
        for (int i = 0; i < 4; i++)
        {
            if (ColorNum == Result[i])
            {
                AnswerNum = ++i * TextNum;
                // 이번 기믹의 정답을 저장함.
                switch (AnswerNum)
                {
                    case 2:
                        correct_answer = MulResult[0];
                        break;
                    case 3:
                        correct_answer = MulResult[1];
                        break;
                    case 4:
                        correct_answer = MulResult[2];
                        break;
                    case 6:
                        correct_answer = MulResult[3];
                        break;
                    case 8:
                        correct_answer = MulResult[4];
                        break;
                    case 9:
                        correct_answer = MulResult[5];
                        break;
                    case 12:
                        correct_answer = MulResult[6];
                        break;
                    case 16:
                        correct_answer = MulResult[7];
                        break;
                    default:
                        Debug.Log("AnswerNum 오류");
                        break;
                }
                break;
            }
        }
    }

    // 이전 기믹 끝나면 작동 해야함.
    public void ButtonSet()
    {
        // 4개의 선택지를 버튼으로 제공
        for (int i = 0; i < ButtonInt.Length; i++)
        {
            int _tmp = Random.Range(0, 8); // 랜덤으로 8개의 값중 하나를 삽입한다.
            ButtonInt[i] = MulResult[_tmp];
            for (int j = 0; j < i; j++) // 중복 체크
            {
                if ((ButtonInt[j] == ButtonInt[i]))
                {
                    i--;
                }
            }
        }

        // 랜덤으로 제공된 선택지에 정답이 없는 경우 랜덤한 위치에 정답을 삽입
        bool Correct = false;
        for(int i=0; i<ButtonInt.Length; i++)
        {
            if(ButtonInt[i] == correct_answer) Correct = true;
        }
        if (!Correct)
        {
            int tmp = Random.Range(0, 4); // 답이 들어갈 버튼 위치를 0 ~ 3으로 랜덤 선택
            ButtonInt[tmp] = correct_answer; // 버튼에 답이 들어가도록 설정
        }

        for (int i=0; i < 4; i++)
        {
            ButtonText[i].text = ButtonInt[i].ToString();
        }
    }

    public void AnswerCheck(int num)
    {
        if (ButtonInt[num] == correct_answer)
        {
            NextQ.SetActive(true);
            PrevQ[0].SetActive(false);
            PrevQ[1].SetActive(false);
            PrevQ[2].SetActive(false);
            Debug.Log("2번째 문제 통과");
        }
    }
}
