package util;

import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.wechatmyself.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ChatContent;
import bean.Friend;
import bean.FriendCircle;

public class DataHandleUtil {
    ///////发送请求
    /**
     * 请求注册
     * @param phone
     * @param name
     * @param password
     * @return 注册请求字符串处理
     */
    public String registerRequest(String phone, String name, String password){
        Map<String,Object> main=new HashMap<String,Object>();
        main.put("phone",phone);
        main.put("name",name);
        main.put("password",password);
        String result = getResult(1101,main);
        return result;

    }

    /**
     * 请求登录
     * @param phone
     * @param password
     * @return Token
     */
    public String loginRequest(String phone, String password){
        Map<String,Object> map=new HashMap<>();
        map.put("phone",phone);
        map.put("password",password);
        return getResult(1102,map);
    }
    /**
     * 登录成功字符串处理
     * @param result
     * @return Token
     */
    public String loginResponse(String result){
        try{
            String id=null;
            if(!TextUtils.isEmpty(result)){
                org.json.JSONObject jsonObject = new org.json.JSONObject(result);
                Log.i("888",result);
                int j=jsonObject.getInt("success");
                id=jsonObject.getString("id");

            }
            return id;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 安全退出
     * @param id
     * @return
     */
    public String quitRequest(String id){
        Map<String,Object> map=new HashMap<>();
        map.put("identify_id",id);
        map.put("success",-1);
        return getResult(1103,map);
    }
    /**
     * 请求好友列表
     * @param identifyId 请求人ID
     * @return 好友集合
     */
    public String getFriendListRequest(int identifyId){
        Map<String,Object> map=new HashMap<>();
        map.put("identify_id",identifyId);
        return getResult(1201,map);
    }
//    /**
//     *  发送加好友请求
//     * @param identifyId
//     * @param otherId
//     * @return
//     */
//    public String getFriendRequest(int identifyId,int otherId ){
//        Map<String,Object> map=new HashMap<>();
//
//        map.put("identify_id",identifyId);
//        map.put("other_id",otherId);
//
//        return getResult();
//    }
    /**
     * 发送消息请求
     * @param identifyId
     * @param chatContents
     * @return
     */
    public String sendChatContentRequest(int identifyId, int other_id,List<ChatContent> chatContents){
        try {
            Map<String,Object> map=new HashMap<>();
            map.put("identify_id",identifyId);
            map.put("other_id",other_id);
            ChatContent chatContent=chatContents.get(0);
            JSONObject obj=new JSONObject();
            obj.put("content",chatContent.getContent());
            map.put("list",obj.toString());
            return getResult(1301,map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *  请求个人朋友圈
     * @param
     * @return
     */
    public String  getFriendCircleRequest(int identifyId, int otherId ){
        Map<String,Object> map=new HashMap<>();
        map.put("identify_id",identifyId);
        map.put("other_id",otherId);
        return getResult(1401,map);
    }
    /**
     *  请求朋友圈
     * @param identifyId
     * @return
     */
    public String getFriendCirclesRequest(int identifyId){
        Map<String,Object> map=new HashMap<>();
        map.put("identify_id",identifyId);
        map.put("flag",1402);
        return getResult(1402,map);
    }
//////////得到数据
    /**
     * 注册返回字符串处理
     * @param result
     * @return 注册结果处理
     */
    public int registerRespones(String result){
        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject(result);
            int success=jsonObject.getInt("success");
            return success;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 安全退出
     * @param result
     * @return
     */
    public boolean quitResponse(String result){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            if (jsonObject.getInt("success")==1){
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Friend> getFriendListResponse(String result){
        JSONObject jsonObject = null;
        List<Friend>lists=new ArrayList<Friend>();
        try {
            jsonObject=new JSONObject(result);
            Log.i("result",result);
            String res= jsonObject.getString("list");

            JSONArray jsonArray = new JSONArray(res);
            for(int i=0;i<jsonArray.length();i++){
                Friend friend=new Friend();
                JSONObject ojb=jsonArray.getJSONObject(i);
                friend.setId(ojb.getInt("id"));
                friend.setName(ojb.getString("name"));
                friend.setCity(ojb.getString("city"));
              //  friend.setPhoneNum(ojb.getString("phone"));
//                friend.setFriendPicPath(ojb.getString("friendPicPath"));
         //       friend.setHeadPicPath(ojb.getString("headPicPath"));
//                friend.setMark(jsonObject.getString("mark"));
                friend.setMcode(ojb.getString("mcode"));
                friend.setProvince(ojb.getString("province"));
                lists.add(friend);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lists;
    }

    /**
     * 发送消息请求
     * @param result
     * @return
     */
    public List<ChatContent> sendChatContentResponse(String result){

        JSONObject jsonObject = null;
        List<ChatContent>lists=new ArrayList<ChatContent>();
        try {

            JSONArray jsonArray = new JSONArray(result);
            for(int i=0;i<jsonArray.length();i++){
                ChatContent ChatContent=new ChatContent();
                jsonObject=jsonArray.getJSONObject(i);
                ChatContent.setId(jsonObject.getInt("id"));
                ChatContent.setContent(jsonObject.getString("content"));
                ChatContent.setCid(jsonObject.getInt("cid"));
                ChatContent.setIsMine(jsonObject.getInt("isMine"));
                ChatContent.setInsertTime(jsonObject.getLong("insertTime"));
                ChatContent.setState(jsonObject.getInt("state"));
                lists.add(ChatContent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lists;
    }
    /**
     *  请求个人朋友圈
     * @param result
     * @return
     */
    public List<FriendCircle> getFriendCircleResponse(String result){
        JSONObject jsonObject = null;
        List<FriendCircle>lists=new ArrayList<FriendCircle>();
        try {

            JSONArray jsonArray = new JSONArray(result);
            for(int i=0;i<jsonArray.length();i++){
                FriendCircle FriendCircle=new FriendCircle();
                jsonObject=jsonArray.getJSONObject(i);
                FriendCircle.setId(jsonObject.getInt("id"));
                FriendCircle.setContent(jsonObject.getString("content"));
                FriendCircle.setUid(jsonObject.getInt("uid"));
                FriendCircle.setIsGood(jsonObject.getInt("isGood"));
                FriendCircle.setInsertTime(jsonObject.getLong("insertTime"));
                FriendCircle.setNumGood(jsonObject.getInt("numGood"));
                lists.add(FriendCircle);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lists;

    }

    private String getResult(int flag, Map<String,Object> main){
        Map<String,Object> map=new HashMap<>();
        map.put("flag",flag);
        map.put("main",main);
        JSONObject jsonObject=new JSONObject(map);
        return jsonObject.toString();
    }


}