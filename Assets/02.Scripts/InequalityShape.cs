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
    private int[] correct_answer = new int[6]; // 정답을 저장한 배열
    private int[] user_answer = new int[6]; // 유저의 답을 저장하는 배열
    // Start is called before the first frame update
    void Start()
    {
        for(int i = 0; i < Shapes.Length; i++)
        {
            ran = Random.Range(0, 3); // 0이면 왼쪽이 크게(>), 1이면 두 분수가 같게(=), 2면 오른쪽이 크게(<)
            Shapes[i].GetComponent<Shape>().SettingStart(ran);
            //Debug.Log(i + "번째 : " + ran);
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
            //Debug.Log(i + "번째 유저답 : " + user_answer[i]);
        }
        if (TorF)
        {
            NextQ[0].SetActive(true);
            NextQ[1].SetActive(true);
            PrevQ.SetActive(false);
            Debug.Log("4번 정답");
        }
    }
}
