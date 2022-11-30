using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.UI;
using Random = UnityEngine.Random;

public class ColorBox : MonoBehaviour
{
    public Image[] img;
    public GameObject NextQ;
    public GameObject PrevQ;

    private int colorBox;
    private int corrent_answer;

    void Start()
    {
        BoxColorSet();
    }

    // ��ư���� �Էµ� ���� �������� Ȯ���ϴ� �Լ�
    public void BtnSet(int num)
    {
        if(num == corrent_answer)
        {
            NextQ.SetActive(true);
            PrevQ.SetActive(false);
        }
    }

    // ���� �ٲ� �ڽ��� ������ ���ϴ� �Լ�
    private void BoxColorSet()
    {
        colorBox = Random.Range(2, img.Length - 2); // ���� �ٲ� �ڽ��� ����
        int[] tmp = new int[colorBox]; // ���� �ٲ� �ڽ��� ��ġ�� �������� ����
        for (int i = 0; i < colorBox; i++)
        {
            tmp[i] = Random.Range(0, img.Length);
            for (int j = 0; j < i; j++) // �ߺ� üũ
            {
                if (tmp[j] == tmp[i]) i--;
            }
        }

        for (int i = 0; i < tmp.Length; i++)
        {
            img[tmp[i]].transform.GetComponent<Image>().color = new Color32(110, 234, 255, 255);
        }
        corrent_answer = colorBox * (10 - colorBox);
    }
}
