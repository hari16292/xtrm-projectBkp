package pojoClasses.getUserPaymentMethods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResData {
    @JsonProperty("PaymentMethodId")
    private String paymentMethodId;
    @JsonProperty("PaymentMethodName")
    private String paymentMethodName;

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }
}
