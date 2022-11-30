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

        // tmp �迭 �������� ����(���� ������ ���ʴ��)
        Array.Sort(tmp);

        // Integer �� �迭 SortBtn�� ���ĵ� tmp�� ����
        SortBtn = tmp;
    }

    public void Color_Button(int btn_num)
    {
        if (SortBtn[BtnCount] == Btn[btn_num]) // ����
        {
            button[btn_num].enabled = false;

            // ���� ��Ϳ� ��ư ������ �� ��°���� �Ѱ���
            GameObject Rules = GameObject.FindGameObjectWithTag("FindRules");
            Rules.GetComponent<Find_Rules>().Note(++btn_num);

            BtnCount++; // ������ ���� Ƚ���� �ø�
            if(BtnCount == 4) // ������ ���� Ƚ���� 4�� �̶��
            {
                Rules.GetComponent<Find_Rules>().Pass();
                // ���� ��� Ȱ��ȭ
                NextQ[0].SetActive(true);
                NextQ[1].SetActive(true);
                NextQ[2].SetActive(true);
                Rules.GetComponent<Find_Rules>().ButtonSet();
                // ��� ��� ��Ȱ��ȭ
                PrevQ[0].SetActive(false);
                PrevQ[1].SetActive(false);
                Debug.Log("1��° ���� ���");
            }
        }else // ����
        {
            Debug.Log("����");
            // ����� �׷��� ȿ���� Ʋ�� ���� �˷���.
        }
    }
}
