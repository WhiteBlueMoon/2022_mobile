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
    public Image[] ImproperColor; // 가분수 색깔
    public GameObject clear;
    
    private int[] MixedInt = new int[3]; // 대분수 값
    private int[] Numerator = new int[3]; // 분자 값
    private int Denominator; // 모든 분수의 공통 분모부분
    private int[] Improper = new int[3]; // 가분수 분자 값
    private int[] Improper_ = new int[3]; // 정답
    private int[] ImproperInteger = new int[3]; // 가분수 색을 결정하는 int 값. 1 빨강 2 초록 3 파랑

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

    // 초기화
    private void Setting()
    {
        for (int i = 0; i < MixedText.Length; i++) // 대분수 설정
        {
            MixedText[i].text = MixedInt[i].ToString();
        }
        for (int i = 0; i < DenomText.Length; i++) // 분모 설정
        {
            DenomText[i].text = Denominator.ToString();
        }
        for (int i = 0; i < 3; i++) // 분자 설정
        {
            String tmp = Numerator[i].ToString();
            tmp = tmp + "\n" + "ㅡ";
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
            tmpStr = tmpStr + "\n" + "ㅡ";
            NumerText[intmp[i] + Numerator.Length].text = tmpStr;
        }

        // 가분수 색을 적색으로 초기화
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
                    Debug.Log("색상 오류");
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
        Debug.Log("유저 정답");
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
            Debug.Log("5번째 문제 통과");
        }
        else Debug.Log("아님");
    }
}
