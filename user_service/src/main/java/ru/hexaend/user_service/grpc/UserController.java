package ru.hexaend.user_service.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.ProtocolStringList;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.stereotype.Controller;
import ru.hexaend.user_service.proto.HealthCheckResponse;
import ru.hexaend.user_service.proto.ListOfStrings;
import ru.hexaend.user_service.proto.ResultResponse;
import ru.hexaend.user_service.proto.SimpleGrpc;
import ru.hexaend.user_service.service.UserService;

import java.util.List;
import java.util.Map;

@GrpcService
@Slf4j
@RequiredArgsConstructor
public class UserController extends SimpleGrpc.SimpleImplBase {
    private final UserService userService;

    @Override
    public void usernamesToIDs(ListOfStrings request, StreamObserver<ResultResponse> responseObserver) {
        List<String> list = request.getItemsList().stream().toList();

        Map<String, String> ids = userService.getUserIDsByUsernames(list);

        ResultResponse response = ResultResponse.newBuilder()
                .putAllResults(ids)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
