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

    // 버튼으로 입력된 값이 정답인지 확인하는 함수
    public void BtnSet(int num)
    {
        if(num == corrent_answer)
        {
            NextQ.SetActive(true);
            PrevQ.SetActive(false);
        }
    }

    // 색이 바뀔 박스의 개수를 정하는 함수
    private void BoxColorSet()
    {
        colorBox = Random.Range(2, img.Length - 2); // 색을 바꿀 박스의 개수
        int[] tmp = new int[colorBox]; // 색을 바꿀 박스의 위치를 랜덤으로 선정
        for (int i = 0; i < colorBox; i++)
        {
            tmp[i] = Random.Range(0, img.Length);
            for (int j = 0; j < i; j++) // 중복 체크
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
