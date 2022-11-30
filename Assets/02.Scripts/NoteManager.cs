using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using Random = UnityEngine.Random;

public class NoteManager : MonoBehaviour
{
    public Text[] text;
    public Button[] button;
    public GameObject[] NextQ;
    public GameObject[] PrevQ;

    private int[] SortBtn;
    private int[] Btn;
    private int BtnCount;

    // Start is called before the first frame update
    void Start()
    {
        int[] tmp = new int[text.Length];
        Btn = new int[tmp.Length];

        for(int i = 0; i < text.Length;i++)
        {
            tmp[i] = Random.Range(2, 21);
            text[i].text = tmp[i].ToString();
            Btn[i] = tmp[i];
            for (int j = 0; j < i; j++)
            {
                if (tmp[j] == tmp[i])
                {
                    i--;
                }
            }
        }

        // tmp 배열 오름차순 정렬(작은 값부터 차례대로)
        Array.Sort(tmp);

        // Integer 형 배열 SortBtn에 정렬된 tmp를 복사
        SortBtn = tmp;
    }

    public void Color_Button(int btn_num)
    {
        if (SortBtn[BtnCount] == Btn[btn_num]) // 정답
        {
            button[btn_num].enabled = false;

            // 다음 기믹에 버튼 색깔이 몇 번째인지 넘겨줌
            GameObject Rules = GameObject.FindGameObjectWithTag("FindRules");
            Rules.GetComponent<Find_Rules>().Note(++btn_num);

            BtnCount++; // 정답을 맞춘 횟수를 늘림
            if(BtnCount == 4) // 정답을 맞춘 횟수가 4번 이라면
            {
                Rules.GetComponent<Find_Rules>().Pass();
                // 다음 기믹 활성화
                NextQ[0].SetActive(true);
                NextQ[1].SetActive(true);
                NextQ[2].SetActive(true);
                Rules.GetComponent<Find_Rules>().ButtonSet();
                // 방금 기믹 비활성화
                PrevQ[0].SetActive(false);
                PrevQ[1].SetActive(false);
                Debug.Log("1번째 문제 통과");
            }
        }else // 오답
        {
            Debug.Log("오답");
            // 사운드와 그래픽 효과로 틀린 것을 알려줌.
        }
    }
}
