#！/bin/bash
pid=`netstat -anp|grep 8031 |awk '{print $6$7}'`
echo "pid:"$pid
if [[ "$pid" =~ ^LISTEN ]] ;
then
    pos=`expr index "${pid}" "/java"`
    # echo ---$pos
    len=`expr length "${pid}"`
    # echo ------$len
    pid_len_after=`expr $len - $pos`
    # echo -----pid_len_after-------$pid_len_after
    pid_len_before=`expr $len - $pid_len_after - 1`
    # echo -----pid_len_before-------$pid_len_before
    listen_len=`expr length "LISTEN"`
    pid_len=`expr $pid_len_before - $listen_len`
    # echo -----pid_len-------$pid_len
    echo ${pid:6:pid_len}
    pid_int=`expr ${pid:6:pid_len}`
    # echo $pid_int
    echo "kill -9 pid:" $pid_int
    # kill process by pid_int
    kill -9 $pid_int
else
    echo -------no-pid-port-8031------
fi
