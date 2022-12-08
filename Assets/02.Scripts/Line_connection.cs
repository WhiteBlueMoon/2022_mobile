using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using Random = UnityEngine.Random;

public class Line_connection : MonoBehaviour
{
    public Text[] MixedText;
    public Text[] NumerText;
    public Text[] DenomText;
    public Image[] ImproperColor; // ���м� ����
    public GameObject clear;
    
    private int[] MixedInt = new int[3]; // ��м� ��
    private int[] Numerator = new int[3]; // ���� ��
    private int Denominator; // ��� �м��� ���� �и�κ�
    private int[] Improper = new int[3]; // ���м� ���� ��
    private int[] Improper_ = new int[3]; // ����
    private int[] ImproperInteger = new int[3]; // ���м� ���� �����ϴ� int ��. 1 ���� 2 �ʷ� 3 �Ķ�

    // Start is called before the first frame update
    void Start()
    {
        Denominator = Random.Range(2, 10);
        for (int i = 0; i < MixedInt.Length; i++)
        {
            MixedInt[i] = Random.Range(1, 5);
            Numerator[i] = Random.Range(1, Denominator);
            for(int j = 0; j < i; j++)
            {
                if (MixedInt[i] == MixedInt[j])
                {
                    i--;
                }
            }
        }

        Setting();
        ColorChange();
    }

    // �ʱ�ȭ
    private void Setting()
    {
        for (int i = 0; i < MixedText.Length; i++) // ��м� ����
        {
            MixedText[i].text = MixedInt[i].ToString();
        }
        for (int i = 0; i < DenomText.Length; i++) // �и� ����
        {
            DenomText[i].text = Denominator.ToString();
        }
        for (int i = 0; i < 3; i++) // ���� ����
        {
            String tmp = Numerator[i].ToString();
            tmp = tmp + "\n" + "��";
            NumerText[i].text = tmp;
        }
        int[] intmp = new int[Numerator.Length];
        for(int i=0; i<Numerator.Length; i++)
        {
            intmp[i] = Random.Range(0, 3);
            for(int j = 0; j < i; j++)
            {
                if (intmp[i] == intmp[j])
                {
                    i--;
                }
            }
        }
        for (int i = 0; i < Numerator.Length; i++)
        {
            Improper[intmp[i]] = (MixedInt[i] * Denominator) + Numerator[i];
            Improper_[i] = (MixedInt[i] * Denominator) + Numerator[i];
            String tmpStr = Improper[intmp[i]].ToString();
            tmpStr = tmpStr + "\n" + "��";
            NumerText[intmp[i] + Numerator.Length].text = tmpStr;
        }

        // ���м� ���� �������� �ʱ�ȭ
        for(int i = 0; i< ImproperColor.Length; i++)
        {
            ImproperInteger[i] = 0;
        }
    }

    private void ColorChange()
    {
        for(int i = 0; i < ImproperColor.Length; i++)
        {
            switch (ImproperInteger[i])
            {
                case 0:
                    ImproperColor[i].color = Color.red;
                    break;
                case 1:
                    ImproperColor[i].color = Color.green;
                    break;
                case 2:
                    ImproperColor[i].color = Color.blue;
                    break;
                default:
                    Debug.Log("���� ����");
                    break;
            }
        }
    }

    public void ButtonInput(int num)
    {
        switch (num)
        {
            case 0:
                ++ImproperInteger[0];
                ++ImproperInteger[2];
                break;
            case 1:
                ++ImproperInteger[1];
                break;
            case 2:
                ++ImproperInteger[1];
                ++ImproperInteger[2];
                break;
        }
        for(int i=0;i< ImproperInteger.Length; i++)
        {
            if (ImproperInteger[i] == 3) ImproperInteger[i] = 0;
        }
        ColorChange();
        check();
    }

    private void check()
    {
        bool TorF = true;
        Debug.Log("���� ����");
        for (int i = 0; i < 3; i++)
        {
            if (Improper_[ImproperInteger[i]] != Improper[i])
            {
                TorF = false;
            }
        }

        if (TorF)
        {
            clear.SetActive(true);
            Debug.Log("5��° ���� ���");
        }
        else Debug.Log("�ƴ�");
    }
}
