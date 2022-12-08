using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Menu : MonoBehaviour
{
    public GameObject menuImg;
    public GameObject[] reBtn;

    public void Btn()
    {
        menuImg.SetActive(true);
        for(int i = 0; i < reBtn.Length; i++)
        {
            reBtn[i].SetActive(true);
        }
    }

    public void returnBtn()
    {
        menuImg.SetActive(false);
        for (int i = 0; i < reBtn.Length; i++)
        {
            reBtn[i].SetActive(false);
        }
    }

    public void exit()
    {
        Application.Quit();
    }
}
