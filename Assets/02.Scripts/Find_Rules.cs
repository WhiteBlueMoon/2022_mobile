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

    private int[] Result = new int[4]; // 1~4�� ���� �ߺ����� �����
    private int[] ButtonInt = new int[4];
    private int[] MulResult = new int[] { 20, 6, 12, 24, 7, 14, 35, 24 }; // ���� ����� �迭
    private int ResultCount = 0;
    private int ColorNum;
    private int TextNum;
    private int AnswerNum;
    private int correct_answer; // �̹� ������ ����
    private int User_answer; // ����ڰ� �Է��� ����

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
                Debug.Log("���� �����Դϴ�.");
                break;
        }
    }

    private void TextSet(int num)
    {
        text.text = num.ToString();
    }

    public void Note(int num)
    {
        Result[ResultCount] = num; // ��ư ��Ϳ��� �ѱ� ���� ������ ����
        ResultCount++;
    }

    public void Pass()
    {
        // �Ѿ�� ��ư ���� ���� ���� int�� ã��
        for (int i = 0; i < 4; i++)
        {
            if (ColorNum == Result[i])
            {
                AnswerNum = ++i * TextNum;
                // �̹� ����� ������ ������.
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
                        Debug.Log("AnswerNum ����");
                        break;
                }
                break;
            }
        }
    }

    // ���� ��� ������ �۵� �ؾ���.
    public void ButtonSet()
    {
        // 4���� �������� ��ư���� ����
        for (int i = 0; i < ButtonInt.Length; i++)
        {
            int _tmp = Random.Range(0, 8); // �������� 8���� ���� �ϳ��� �����Ѵ�.
            ButtonInt[i] = MulResult[_tmp];
            for (int j = 0; j < i; j++) // �ߺ� üũ
            {
                if ((ButtonInt[j] == ButtonInt[i]))
                {
                    i--;
                }
            }
        }

        // �������� ������ �������� ������ ���� ��� ������ ��ġ�� ������ ����
        bool Correct = false;
        for(int i=0; i<ButtonInt.Length; i++)
        {
            if(ButtonInt[i] == correct_answer) Correct = true;
        }
        if (!Correct)
        {
            int tmp = Random.Range(0, 4); // ���� �� ��ư ��ġ�� 0 ~ 3���� ���� ����
            ButtonInt[tmp] = correct_answer; // ��ư�� ���� ������ ����
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
            Debug.Log("2��° ���� ���");
        }
    }
}
