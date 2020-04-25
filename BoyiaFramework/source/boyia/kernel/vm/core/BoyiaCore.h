/*
============================================================================
 Name        : BoyiaCore.h
 Author      : yanbo
 Version     : BoyiaCore v0.1
 Copyright   : All Copyright Reserved
 Date        : 2012-2-5
 Description : Support Original
============================================================================
*/

#ifndef BoyiaCore_h
#define BoyiaCore_h

#include "BoyiaValue.h"

typedef struct {
    LUintPtr mNameKey;
    LInt (*mAddr)();
} NativeFunction;

typedef struct {
    NativeFunction* mFun;
    LInt mSize;
} NativeFunMap;

LVoid CompileCode(char* code);
LVoid CallFunction(char* funcCall, LVoid* ret);
LInt GetLocalSize();
LVoid* GetLocalValue(LInt idx);
LVoid SetNativeResult(LVoid* result);
LVoid GetLocalStack(LInt* stack, LInt* size);
LVoid* GetNativeResult();
LVoid* CopyObject(LUintPtr hashKey, LInt size);
LVoid ValueCopy(BoyiaValue* dest, BoyiaValue* src);
LVoid GetGlobalTable(LInt* table, LInt* size);
LVoid NativeCall(BoyiaValue* obj);
LVoid LocalPush(BoyiaValue* value);
LVoid SaveLocalSize();
LVoid* InitVM();
LVoid DestroyVM(LVoid* vm);
LInt CreateObject();
LVoid InitNativeFun(NativeFunction* funs);
LVoid ChangeVM(LVoid* vm);
LVoid CacheVMCode();
LVoid LoadStringTable(BoyiaStr* stringTable, LInt size);
LVoid LoadInstructions(LVoid* buffer, LInt size);
LVoid LoadEntryTable(LVoid* buffer, LInt size);

#endif
