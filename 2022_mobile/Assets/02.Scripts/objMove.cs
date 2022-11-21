using System.Collections;
using System.Collections.Generic;
using UnityEngine;



public class objMove : MonoBehaviour
{
    private Vector3 mOffset;
    private float mZCoord;
    private float mXCoord;


    // 마우스로 클릭했을 때 자동으로 호출
    void OnMouseDown()
    {
        mZCoord = Camera.main.WorldToScreenPoint(gameObject.transform.position).z;
        mXCoord = Camera.main.WorldToScreenPoint(gameObject.transform.position).y;

        // Store offset = gameobject world pos - mouse world pos
        mOffset = gameObject.transform.position - GetMouseAsWorldPoint();
    }


    private Vector3 GetMouseAsWorldPoint()
    {
        // Pixel coordinates of mouse (x,y)
        Vector3 mousePoint = Input.mousePosition;

        // z coordinate of game object on screen
        mousePoint.z = mZCoord;
        mousePoint.y = mXCoord;

        Debug.Log(mousePoint);
        // Convert it to world points
        return Camera.main.ScreenToWorldPoint(mousePoint);
    }


    void OnMouseDrag()
    {
        Vector3 objectPos = GetMouseAsWorldPoint() + mOffset;
        objectPos.y = 0.1f;
        transform.position = objectPos;
        
    }
}