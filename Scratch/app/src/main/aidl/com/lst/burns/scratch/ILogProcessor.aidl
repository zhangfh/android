// ILogProcessor.aidl
package com.lst.burns.scratch;

// Declare any non-default types here with import statements

interface ILogProcessor {
	void reset(String buffer);
	void run(int type);
	void restart(int type);
	void stop();
	void write(String file, String tag);
}
