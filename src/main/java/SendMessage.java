
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

public class SendMessage {

    public static void main(String[] args) {
        Region region = Region.US_EAST_1;


        String queueURL = "https://sqs.us-east-1.amazonaws.com/890272390288/messaging-app-queue";
        String bucketName = "mybucket1pp1";
        String fileName = "values.csv";

        SqsClient sqsClient = SqsClient.builder().region(region).build();

        SendMessageRequest sendRequest = SendMessageRequest.builder().queueUrl(queueURL)
                .messageBody(bucketName + ";" + fileName).build();

        SendMessageResponse sqsResponse = sqsClient.sendMessage(sendRequest);

        System.out.println(
                sqsResponse.messageId() + " Message sent. Status is " + sqsResponse.sdkHttpResponse().statusCode());
    }
}