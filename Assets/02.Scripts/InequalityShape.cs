using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using Random = UnityEngine.Random;

public class InequalityShape : MonoBehaviour
{
    public GameObject[] Shapes;
    public GameObject[] NextQ;
    public GameObject PrevQ;

    private int ran;
    private int[] correct_answer = new int[6]; // ������ ������ �迭
    private int[] user_answer = new int[6]; // ������ ���� �����ϴ� �迭
    // Start is called before the first frame update
    void Start()
    {
        for(int i = 0; i < Shapes.Length; i++)
        {
            ran = Random.Range(0, 3); // 0�̸� ������ ũ��(>), 1�̸� �� �м��� ����(=), 2�� �������� ũ��(<)
            Shapes[i].GetComponent<Shape>().SettingStart(ran);
            //Debug.Log(i + "��° : " + ran);
            correct_answer[i] = ran;
        }
    }

    public void Check(int num, int Btnnum)
    {
        user_answer[Btnnum] = num;
        bool TorF = true;
        for(int i = 0; i < correct_answer.Length; i++)
        {
            if(correct_answer[i] != user_answer[i])
            {
                TorF = false;
            }
            //Debug.Log(i + "��° ������ : " + user_answer[i]);
        }
        if (TorF)
        {
            NextQ[0].SetActive(true);
            NextQ[1].SetActive(true);
            PrevQ.SetActive(false);
            Debug.Log("4�� ����");
        }
    }
}
