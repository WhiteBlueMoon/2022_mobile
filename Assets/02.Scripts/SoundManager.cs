using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SoundManager : MonoBehaviour
{
    public AudioSource clickSound;

    private void Update()
    {
        if(Input.touchCount > 0)
        {
            Touch touch = Input.GetTouch(0);
            if(touch.phase == TouchPhase.Began)
            {
                clickSound.Play();
                Debug.Log("����� ��ġ Ȯ��");
            }
        }

        if (Input.GetMouseButtonDown(0))
        {
            clickSound.Play();
            Debug.Log("pc Ŭ�� Ȯ��");
        }
    }
}
