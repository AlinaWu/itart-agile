package top.itart.agile.common.manager.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import top.itart.agile.common.manager.VisitManager;
import top.itart.agile.common.model.VisitLog;

@Service
@Scope("singleton")
public class VisitManagerImpl implements VisitManager{

	private ExecutorService pool = Executors.newFixedThreadPool(10);
	
	@Override
	public void addVisit(VisitLog visit) {
		pool.execute(new Runnable(){
			@Override
			public void run() {
				
			}
		});
	}

}
