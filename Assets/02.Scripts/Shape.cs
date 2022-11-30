using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using Random = UnityEngine.Random;

public class Shape : MonoBehaviour
{
    public Image[] shapeColor;
    public Sprite[] ButtonSpr; // 버튼 이미지
    public Text DenomText; // 분모 텍스트
    public Text NumerText; // 분자 텍스트
    public Image Btnimg; // 버튼 이미지
    public GameObject Inequality;

    private int Denominator; // 분모의 크기 최소 5 / 최대 15
    private int Numerator; // 분자의 크기
    private int BtnInt = 0; // 버튼 이미지를 확인하는 정수

    public void SettingStart(int num) // num값은 상위에서 넘겨준 부등호 값
    {
        DenominatorSet(); // 분모 세팅 함수 호출
        NumeratorSet(); // 분자 세팅 함수 호출
        TextSet(num); // 텍스트 세팅 함수 호출
    }

    private void TextSet(int num_) // 텍스트 세팅
    {
        DenomText.text = Denominator.ToString();
        int tmpInt = 0;
        switch (num_)
        {
            case 0:
                tmpInt = Random.Range(1,Numerator);
                break;
            case 1:
                tmpInt = Numerator;
                break;
            case 2:
                tmpInt = Random.Range(Numerator + 1, Denominator);
                break;
            default:
                Debug.Log("부등호 오류");
                break;
        }
        String tmp = tmpInt.ToString();
        tmp = tmp + "\n" + "ㅡ";
        NumerText.text = tmp;
    }

    private void DenominatorSet() // 분모 세팅
    {
        Denominator = Random.Range(5, shapeColor.Length + 1); // 분모의 크기를 랜덤으로 설정
        //Debug.Log("분모 : " + Denominator);
        for (int i = 0; i < Denominator; i++) // 분모의 크기에 맞게 투명화 해제
        {
            Color color = shapeColor[i].color;
            color.a = 1f;
            shapeColor[i].color = color;
        }
    }

    private void NumeratorSet() // 분자 세팅
    {
        Numerator = Random.Range(2, Denominator); // 분자의 크기를 분모보다 적은 수 중에서 랜덤으로 삽입
        //Debug.Log("분자 : " + Numerator);
        for (int i = 0; i < Numerator; i++)
        {
            shapeColor[i].color = Color.blue;
        }
    }

    public void ButtonImgChange(int Btnnum) // 버튼 이미지 변경 함수
    {
        BtnInt += 1;
        if (BtnInt == 3) BtnInt = 0;
        Btnimg.sprite = ButtonSpr[BtnInt];
        Inequality.GetComponent<InequalityShape>().Check(BtnInt, Btnnum);
    }
}