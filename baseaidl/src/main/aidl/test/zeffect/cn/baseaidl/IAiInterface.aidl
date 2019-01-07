// IAiInterface.aidl
package test.zeffect.cn.baseaidl;
import test.zeffect.cn.baseaidl.IAICallback;
// Declare any non-default types here with import statements

interface IAiInterface {
    void init(IAICallback callback);
    String getToken();
}
