package query.demo.Service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import query.demo.Entity.Receiver;
import query.demo.Service.ReceiverService;
import query.demo.dao.ReceiverDao;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceiverServiceImpl implements ReceiverService {
    @Resource
    private ReceiverDao receiverDao;
    Receiver r=new Receiver();
    
    
    public Receiver findOneById(long id){





    	return receiverDao.findOneById(id).orElse(null);
    }


    /*
            根据email判断是否存在该收件人，若不存在将其加入到数据库中。
             */
    @Override
    public List<Receiver> checkAndAdd(List<String> emails) {



        List<Receiver> receivers = new ArrayList<>();
        Optional<List<String>> optionalStrings = Optional.ofNullable(emails.stream().
                distinct().
                filter((email) -> receiverDao.existsByEmail(email)).
                collect(Collectors.toList()));
        optionalStrings.ifPresent((u) -> {
            for (String s : u) {
            Receiver r = new Receiver();
                r.setEmail(s);
                receiverDao.save(r);
                receivers.add(r);
            }
        });
        return receivers;

    }







}
