package query.demo.Service.Impl;

import org.springframework.stereotype.Service;
import query.demo.Entity.Receiver;
import query.demo.Entity.ReceiverGroup;
import query.demo.dao.ReceiverGroupDao;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceiverGroupServiceImpl {
    @Resource
    private ReceiverGroupDao reveiverGroupDao;

    public List<Receiver > findReceivers(List<Long> receiverGroups)  {
//需不需要考虑找不到的的情况？

        Optional<List<ReceiverGroup>> receivers=reveiverGroupDao.findByIdIn(receiverGroups);

/*
List<Receiver> rr=receivers.get()
        .stream()
        .flatMap((receiverGroup -> receiverGroup.getReceivers().stream()))
        .distinct()
        .collect(Collectors.toList());
*/




    /*  return receivers.isPresent()?  receivers.get()
                .stream()
                .flatMap((receiverGroup -> receiverGroup.getReceivers().stream()))
                .distinct()
                .collect(Collectors.toList()):null;*/

     return  reveiverGroupDao.findByIdIn(receiverGroups).get()
             .stream()
             .flatMap((receiverGroup -> receiverGroup.getReceivers().stream()))
             .distinct()
             .collect(Collectors.toList());
    }






}
