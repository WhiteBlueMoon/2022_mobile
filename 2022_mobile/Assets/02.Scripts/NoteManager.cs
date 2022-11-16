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
            BtnCount++; // ������ ���� Ƚ���� �ø�
            if(BtnCount == 4) // ������ ���� Ƚ���� 4�� �̶��
            {
                Debug.Log("���");
            }
        }else // ����
        {
            Debug.Log("����");
            // ����� �׷��� ȿ���� Ʋ�� ���� �˷���.
        }
    }
}
