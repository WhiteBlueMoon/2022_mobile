using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Target_Fllowing : MonoBehaviour
{
    public Transform Target; // 추적할 오브젝트


    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        // 추적 코드
        transform.position = Camera.main.WorldToScreenPoint(Target.position);
    }
}
