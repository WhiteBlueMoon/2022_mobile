using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Target_Fllowing : MonoBehaviour
{
    public Transform Target; // ������ ������Ʈ


    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        // ���� �ڵ�
        transform.position = Camera.main.WorldToScreenPoint(Target.position);
    }
}
