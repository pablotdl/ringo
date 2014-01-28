package ar.edu.unicen.ringo.mockapp.ui;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.edu.unicen.ringo.mockapp.core.CallGeneratorTask;

/**
 * Starts/stops the invocation sampler
 */
@Controller("lifecycle")
@RequestMapping("/generator")
public class LifecycleController {

	@Autowired
	private TaskScheduler scheduler;

	@Autowired
	private CallGeneratorTask generator;

	@Value("${sampleInterval}")
	private int sampleInterval;

	private ScheduledFuture<?> future;

	@ResponseBody
	@RequestMapping("start")
	public String start() {
		if (future != null) {
			throw new AlreadyInRequestedState();
		}
		future = scheduler.scheduleAtFixedRate(generator, sampleInterval);
		return "Sampler successfully started";
	}

	@ResponseBody
	@RequestMapping("stop")
	public String stop() {
		if (future == null) {
			throw new AlreadyInRequestedState();
		}
		future.cancel(false);
		return "Sampler successfully stopped";
	}
}
