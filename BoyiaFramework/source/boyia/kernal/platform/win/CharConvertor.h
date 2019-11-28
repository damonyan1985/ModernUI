#ifndef CharConvertor_h
#define CharConvertor_h

#include "UtilString.h"

#include <string>
#include<windows.h>

//using std::string;
using std::wstring;

namespace yanbo {
class CharConvertor {
public:
	static wstring CharToWchar(const char* c, size_t m_encode = CP_ACP);
	static LVoid WcharToChar(const wchar_t* wp, String& text, size_t m_encode = CP_ACP);
};
}

#endif