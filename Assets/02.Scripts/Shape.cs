using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using Random = UnityEngine.Random;

public class Shape : MonoBehaviour
{
    public Image[] shapeColor;
    public Sprite[] ButtonSpr; // ��ư �̹���
    public Text DenomText; // �и� �ؽ�Ʈ
    public Text NumerText; // ���� �ؽ�Ʈ
    public Image Btnimg; // ��ư �̹���
    public GameObject Inequality;

    private int Denominator; // �и��� ũ�� �ּ� 5 / �ִ� 15
    private int Numerator; // ������ ũ��
    private int BtnInt = 0; // ��ư �̹����� Ȯ���ϴ� ����

    public void SettingStart(int num) // num���� �������� �Ѱ��� �ε�ȣ ��
    {
        DenominatorSet(); // �и� ���� �Լ� ȣ��
        NumeratorSet(); // ���� ���� �Լ� ȣ��
        TextSet(num); // �ؽ�Ʈ ���� �Լ� ȣ��
    }

    private void TextSet(int num_) // �ؽ�Ʈ ����
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
                Debug.Log("�ε�ȣ ����");
                break;
        }
        String tmp = tmpInt.ToString();
        tmp = tmp + "\n" + "��";
        NumerText.text = tmp;
    }

    private void DenominatorSet() // �и� ����
    {
        Denominator = Random.Range(5, shapeColor.Length + 1); // �и��� ũ�⸦ �������� ����
        //Debug.Log("�и� : " + Denominator);
        for (int i = 0; i < Denominator; i++) // �и��� ũ�⿡ �°� ����ȭ ����
        {
            Color color = shapeColor[i].color;
            color.a = 1f;
            shapeColor[i].color = color;
        }
    }

    private void NumeratorSet() // ���� ����
    {
        Numerator = Random.Range(2, Denominator); // ������ ũ�⸦ �и𺸴� ���� �� �߿��� �������� ����
        //Debug.Log("���� : " + Numerator);
        for (int i = 0; i < Numerator; i++)
        {
            shapeColor[i].color = Color.blue;
        }
    }

    public void ButtonImgChange(int Btnnum) // ��ư �̹��� ���� �Լ�
    {
        BtnInt += 1;
        if (BtnInt == 3) BtnInt = 0;
        Btnimg.sprite = ButtonSpr[BtnInt];
        Inequality.GetComponent<InequalityShape>().Check(BtnInt, Btnnum);
    }
}